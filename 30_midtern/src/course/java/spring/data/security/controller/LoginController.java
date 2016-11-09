package course.java.spring.data.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import course.java.spring.data.model.UserModel;
import course.java.spring.data.service.UserService;

@Controller
public class LoginController{
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(UserModel userModel, HttpServletRequest request){
		userModel.setRole("customer");
		userModel = userService.createUser(userModel);
		
		if(userModel.getUserId() != 0){
			request.setAttribute("message", "已註冊，請登入");
		}else{
			request.setAttribute("message", "錯誤：帳戶已存在，請使用其他名稱註冊");
		}
		return new ModelAndView("login", "UserModel", userModel);
    }
	
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public ModelAndView loginError(HttpServletRequest request){
		request.setAttribute("message", "錯誤：認證失敗，請重新登入");
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/accessError", method = RequestMethod.GET)
	public ModelAndView accessError(HttpServletRequest request){
		request.setAttribute("message", "錯誤：授權失敗，請登入");
		return new ModelAndView("login");
	}
}