package course.java.spring.data.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import course.java.spring.data.model.TransactionModel;
import course.java.spring.data.service.TransactionService;
import java.util.List;

@Controller
public class TransactionController {
	@Autowired
	TransactionService transactionService;

    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ModelAndView transaction(HttpServletRequest request){
    	List<TransactionModel> transactionModelList = transactionService.listTransactions();
    	
    	if(transactionModelList.size() == 0) {
    		request.setAttribute("message", "無任何交易");
    	}
		return new ModelAndView("transaction", "TransactionModelList", transactionModelList);
    } 
}