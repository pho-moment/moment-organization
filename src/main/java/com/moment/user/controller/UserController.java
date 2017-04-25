package com.moment.user.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moment.common.util.SpringUtil;
import com.moment.user.domain.UserVO;
import com.moment.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	
	/*测试*/
	@RequestMapping("/list")
	public String list(HttpSession session,HttpServletRequest request){
		System.out.println("uri:"+request.getRequestURI());
		return "user/list";
	}
	@RequestMapping("/doedit")
	public ModelAndView doAdd() throws Throwable{
		ModelAndView model=new ModelAndView("user/list");
		System.out.println("112232123");
		UserVO user = new UserVO();
		user.setEmail("eamil");
		user.setPassword("123");
		user.setName("aaa");
		int a=service.addUser(user);
		System.out.println(a);
		return model;
	}
}
