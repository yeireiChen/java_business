package midterm.java.spring.data.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import midterm.java.spring.data.model.AccountModel;
import midterm.java.spring.data.service.AccountService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController{
	@Autowired
	AccountService accountService;
	
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView account(HttpServletRequest request){
    	List<AccountModel> accountModelList = accountService.listAccounts();
    	if(accountModelList.size() == 0) {
    		request.setAttribute("message", "無任何帳戶");
    	}
		return new ModelAndView("account", "AccountModelList", accountModelList);
    }
}