package course.java.spring.data.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import course.java.spring.data.springdata.UserRepository;
import course.java.spring.data.springdata.AccountRepository;
import course.java.spring.data.entity.User;
import course.java.spring.data.entity.Account;
import course.java.spring.data.entity.Transaction;
import course.java.spring.data.model.AccountModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional("jpaTransactionManager")
public class AccountService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public AccountModel getAccountDetails() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		AccountModel accountModel = new AccountModel();
		if(user.getRole().equals("customer")){
			accountModel.setAccountId(user.getAccount().getId());
			accountModel.setUsername(user.getUsername());
			accountModel.setBalance(user.getAccount().getBalance());
		}
		return accountModel;
	}
	
	public AccountModel operation(int amount) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		AccountModel accountModel = null;
		
		if(amount != 0){
			int balance = user.getAccount().getBalance();
			balance += amount;
			
			user.getAccount().setBalance(balance);
					
			Transaction transaction = new Transaction();
			transaction.setUser(user);
			transaction.setAmount(amount);
			transaction.setBalance(balance);
			transaction.setTimeStamp(new Date());
			user.getTransactions().add(transaction);
			
			userRepository.save(user);
			
			accountModel = new AccountModel();
			accountModel.setAccountId(user.getAccount().getId());
			accountModel.setUsername(username);
			accountModel.setBalance(balance);
		}else{
			userRepository.delete(user.getId());
		}
		return accountModel;
	}
	
	public List<AccountModel> listAccounts() {
		List<Account> accountList = (List<Account>) accountRepository.findAll();
		List<AccountModel> accountModelList = new ArrayList<AccountModel>();
		AccountModel accountModel;
		for (Account account : accountList) {
			accountModel = new AccountModel();
			accountModel.setAccountId(account.getId());
			accountModel.setUsername(account.getUser().getUsername());
			accountModel.setBalance(account.getBalance());
			
			accountModelList.add(accountModel);
		}
		return accountModelList;
	}
}