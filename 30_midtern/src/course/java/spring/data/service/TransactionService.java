package course.java.spring.data.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import course.java.spring.data.entity.Transaction;
import course.java.spring.data.entity.User;
import course.java.spring.data.model.StatementModel;
import course.java.spring.data.model.TransactionModel;
import course.java.spring.data.springdata.TransactionRepository;
import course.java.spring.data.springdata.UserRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional("jpaTransactionManager")
public class TransactionService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public StatementModel listStatements() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		List<TransactionModel> transactionModelList = new ArrayList<TransactionModel>();
		Set<Transaction> userTransactionList = user.getTransactions();
		TransactionModel transactionModel; 
		
		for(Transaction transaction : userTransactionList){
			transactionModel = new TransactionModel();
			transactionModel.setTransactionId(transaction.getId());
			transactionModel.setType(transaction.getAmount() > 0 ? "存款": "提款");
			transactionModel.setAmount(Math.abs(transaction.getAmount()));
			transactionModel.setBalance(transaction.getBalance());				
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			transactionModel.setTimeStamp(sdf.format(transaction.getTimeStamp()));

			transactionModelList.add(transactionModel);
		}
		StatementModel statementModel = new StatementModel();
		statementModel.setUsername(username);
		statementModel.setBalance(user.getAccount().getBalance());
		statementModel.setStatementList(transactionModelList);
		
		return statementModel;
	}
	
	public List<TransactionModel> listTransactions() {
		List<Transaction> transactionList = (List<Transaction>) transactionRepository.findAll();
		List<TransactionModel> transactionModelList = new ArrayList<TransactionModel>();
		TransactionModel transactionModel;
		
		for (Transaction transaction : transactionList) {
			transactionModel = new TransactionModel();
			transactionModel.setTransactionId(transaction.getId());
			transactionModel.setUsername(transaction.getUser().getUsername());
			transactionModel.setType(transaction.getAmount() > 0 ? "存款": "提款");
			transactionModel.setAmount(Math.abs(transaction.getAmount()));
			transactionModel.setBalance(transaction.getBalance());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			transactionModel.setTimeStamp(sdf.format(transaction.getTimeStamp()));
			
			transactionModelList.add(transactionModel);
		}
		return transactionModelList;
	}
}