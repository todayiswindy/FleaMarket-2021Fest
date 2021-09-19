package com.yanado.controller.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.controller.user.UserSessionUtils;
import com.yanado.dao.OrderDAO;
import com.yanado.dto.Order;

@Controller
@SessionAttributes("order")
@RequestMapping("/order/view")
public class ViewOrderController {
	@Autowired
	OrderDAO orderDAO;
	
	@ModelAttribute("order")
	@RequestMapping("/result")
	public ModelAndView viewOrder(@Valid @ModelAttribute("order") Order order, BindingResult result,  SessionStatus status){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/result");
		mav.addObject("order",order);
		return mav;
		
	}

	

}
