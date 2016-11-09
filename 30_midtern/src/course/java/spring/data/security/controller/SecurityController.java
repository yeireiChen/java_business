package course.java.spring.data.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import course.java.spring.data.model.UserModel;
import course.java.spring.data.service.UserService;
import java.util.List;

@Controller
@RequestMapping(value="/security")
public class SecurityController {
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	private ModelAndView index(){
		List<UserModel> userModelList = userService.listManagers();
		return new ModelAndView("security", "UserModelList", userModelList);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(UserModel userModel, HttpServletRequest request){
		userModel.setPassword(userModel.getUsername().substring(0, 1).toLowerCase() + "1234");
		userModel.setRole("manager");
		userModel = userService.createUser(userModel);
		
		if(userModel.getUserId() != 0){
			String message = userModel.getUsername() + "　權限已新增";
			request.setAttribute("message", message);
		}else{
			request.setAttribute("message", "錯誤：帳戶已存在，請使用其他名稱註冊");
		}
		List<UserModel> userModelList = userService.listManagers();
		
		return new ModelAndView("security", "UserModelList", userModelList);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(UserModel userModel, HttpServletRequest request){
		userModel =  userService.updateUser(userModel);
		
		if(userModel.getUserId() != 0){
			String message = userModel.getUsername() + "　權限已修改";
			request.setAttribute("message", message);
		}else{
			request.setAttribute("message", "錯誤：權限修改失敗");
		}
		List<UserModel> userModelList = userService.listManagers();
		
		return new ModelAndView("security", "UserModelList", userModelList);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(UserModel userModel, HttpServletRequest request){
    	userModel = userService.deleteUser(userModel);
    	
    	if(userModel.getUserId() != 0){
    		String message = userModel.getUsername() + "　權限已刪除";
			request.setAttribute("message", message);
		}else{
			request.setAttribute("message", "錯誤：權限刪除失敗");
		}
		List<UserModel> userModelList = userService.listManagers();
		
		return new ModelAndView("security", "UserModelList", userModelList);
	}
}