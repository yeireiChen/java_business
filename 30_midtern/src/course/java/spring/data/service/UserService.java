package course.java.spring.data.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import course.java.spring.data.entity.Account;
import course.java.spring.data.entity.User;
import course.java.spring.data.model.UserModel;
import course.java.spring.data.springdata.AccountRepository;
import course.java.spring.data.springdata.UserRepository;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional("jpaTransactionManager")
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	
	public UserModel createUser(UserModel userModel) {
		User user = userRepository.findByUsername(userModel.getUsername());
		String role = userModel.getRole();
		if (user == null) {
			user = new User();
			user.setUsername(userModel.getUsername());
			user.setPassword(userModel.getPassword());
			user.setRole(role);
			if(role.equals("customer")){
				Account account = new Account();
				account.setBalance(0);
				account.setUser(user);
				user.setAccount(account);
			}
			user = userRepository.save(user);
			userModel.setUserId(user.getId());
		} else {
			userModel.setUsername(null);
			userModel.setPassword(null);
		}
		return userModel;
	}
	public UserModel updateUser(UserModel userModel) {
		User user = userRepository.findOne(userModel.getUserId());
		
		if (user != null) {
			user.setRole(userModel.getRole());
			userRepository.save(user);
		}else{
			userModel.setUserId(0);
		}
		return userModel;
	}
	
	public UserModel deleteUser(UserModel userModel) {
		User user = userRepository.findOne(userModel.getUserId());
		
		if(user != null) {
			userRepository.delete(user.getId());
		}else{
			userModel.setUserId(0);
		}
		return userModel;
	}
	
	public List<UserModel> listManagers() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> userList = (List<User>) userRepository.findByNotRole("customer");
		List<UserModel> userModelList = new ArrayList<UserModel>();
		UserModel userModel;
		for (User user : userList) {
			if(!user.getUsername().equals(username)){
				userModel = new UserModel();
				userModel.setUserId(user.getId());
				userModel.setUsername(user.getUsername());
				userModel.setRole(user.getRole());
				userModelList.add(userModel);
			}
		}
		return userModelList;
	}
}