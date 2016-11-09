package course.java.spring.data.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import course.java.spring.data.model.AccountModel;
import course.java.spring.data.model.StatementModel;
import course.java.spring.data.service.AccountService;
import course.java.spring.data.service.TransactionService;

@Controller
public class OperationController{
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(value = "/operation", method = RequestMethod.GET)
	private ModelAndView index(HttpServletRequest request){
		AccountModel accountModel = accountService.getAccountDetails();
		if(accountModel.getAccountId() != 0) {
			return new ModelAndView("operation", "AccountModel", accountModel);
		}else{
			request.setAttribute("message", "提醒：管理者並無交易功能");
			return new ModelAndView("operation");
		}
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public ModelAndView deposit(HttpServletRequest request){
		int amount = Integer.valueOf(request.getParameter("amount"));
		AccountModel accountModel;
		
		if(amount > 0){
			accountModel = accountService.operation(amount);
		}else{
			accountModel = accountService.getAccountDetails();
		}
		return new ModelAndView("operation", "AccountModel", accountModel);
    }
    
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public ModelAndView withdraw(HttpServletRequest request){
		int amount = Integer.valueOf(request.getParameter("amount"));
		AccountModel accountModel = accountService.getAccountDetails();
    	int balance = accountModel.getBalance();
    	
		if(amount > 0) {
			if(balance < amount){
				request.setAttribute("message", "錯誤：餘額不足");
			}else{
				accountModel = accountService.operation(-amount);
			}
		}
		return new ModelAndView("operation", "AccountModel", accountModel);
    }
	
    @RequestMapping(value = "/statement", method = RequestMethod.POST)
    public ModelAndView statement(HttpServletRequest request){
    	StatementModel statementModel = transactionService.listStatements();
    	
    	if(statementModel.getStatementList().size() == 0){
    		request.setAttribute("message", "無任何交易");
    	}
		return new ModelAndView("statement", "StatementModel", statementModel);
    }
    
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public ModelAndView close(){
    	int amount = 0;
        accountService.operation(amount);
		return new ModelAndView("redirect:/");
    }
}