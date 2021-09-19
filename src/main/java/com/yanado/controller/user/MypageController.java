package com.yanado.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.dao.BuyerDAO;
import com.yanado.dao.OrderDAO;
import com.yanado.dao.ProductDAO;
import com.yanado.dao.UserDAO;
import com.yanado.dto.Buyer;
import com.yanado.dto.Order;
import com.yanado.dto.Product;
import com.yanado.dto.User;

// 해당 회원 아이디로 정보 가져와서 마이페이지로 이동
@Controller
public class MypageController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private BuyerDAO buyerDAO;

	@RequestMapping("user/mypageMain")
	protected ModelAndView service(HttpServletRequest request) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		User dto = userDAO.getUserByUserId(userId);

		request.setAttribute("dto", dto);

		mav.setViewName("user/mypageMain");
		mav.addObject("user", dto);
		return mav;

	}

	// 내가 올린 쇼핑 리스트
	@RequestMapping("user/list/myProduct")
	public ModelAndView viewShoppingByUserId(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		List<Product> shopping = productDAO.getProductBySupplierId(userId);
		ModelAndView mav = new ModelAndView("shoppingList");
		mav.setViewName("shopping/myList");
		mav.addObject("shoppingList", shopping);
		return mav;

	}

	// 내 주문 리스트
	@RequestMapping("user/list/order")
	public ModelAndView viewOrderByUserId(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		List<Order> order = (List<Order>) orderDAO.getOrderByUserId(userId);
		mav.setViewName("order/myList");
		mav.addObject("orderList", order);
		
		return mav;

	}
}