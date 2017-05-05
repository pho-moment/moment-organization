package com.moment.user.controller;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.moment.common.domain.CurrentUser;
import com.moment.common.domain.JsonResult;
import com.moment.common.util.RegexValidateUtil;
import com.moment.grade.domain.GradeVO;
import com.moment.grade.service.GradeService;
import com.moment.pic.domain.PicVO;
import com.moment.pic.service.PicService;
import com.moment.user.domain.UserVO;
import com.moment.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private GradeService gservice;
	@Autowired
	private PicService pservice ;
		
	
	@RequestMapping("/doregister")
	public ModelAndView doRegister(HttpServletRequest request,UserVO user){
		ModelAndView mv = new ModelAndView();
		
		//获取用户的账号
		String account = user.getAccount();
		System.out.println(account);
		//用正则表达式检查用户输入的内容是否是邮箱或手机号
		if(RegexValidateUtil.checkEmail(account)||RegexValidateUtil.checkMobileNumber(account)){
			if(RegexValidateUtil.checkEmail(account)){
				user.setEmail(account);
			}else{
				user.setPhonum(account);
			}
		}else{
			mv.setViewName("user/register");
			request.setAttribute("msg", "您输入的邮箱地址或手机号码格式有误！");
			return mv;
		}
		
		//获取用户两次输入的密码
		String password = user.getPassword();
		System.out.println("用户第一次输入的密码为："+password);
		String confirmpwd = request.getParameter("confirmpwd");
		System.out.println("用户第二次输入的密码为："+confirmpwd);
		if(!password.equals(confirmpwd)){
			mv.setViewName("user/register");
			request.setAttribute("msg", "您两次输入的密码不一致！");
			return mv;
		}
		
		//获取用户输入的验证码
		String code = request.getParameter("code");
		System.out.println("用户输入的验证码："+code);
		//获取的验证码
		String sessionCode = (String)request.getSession().getAttribute("sessionCode");
		System.out.println("客户端保存的验证码："+sessionCode);
		if(sessionCode.equalsIgnoreCase(code)){
			mv.setViewName("user/login");
			request.setAttribute("msg", "注册成功，请登录！");
			try {
				service.addUser(user);
			} catch (Throwable e) {
				e.printStackTrace();
				mv.setViewName("user/register");
		        request.setAttribute("msg", "注册失败！");
			}
		}else{
			mv.setViewName("user/register");
			request.setAttribute("msg", "验证码错误！");
		}
		return mv;
	}
	
	@RequestMapping("/login")
	public String login(){
		return "user/login";
	}
	
	@RequestMapping("/dologin")
	public @ResponseBody JsonResult doLogin(HttpServletRequest request,UserVO user,HttpSession session,HttpServletResponse response,JsonResult jsonResult,String remember){
		UserVO user1=null;
		try {
			user1 = service.checkLogin(user.getAccount(), user.getPassword());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if(user1!=null){//登录成功
			//获取用户等级
			GradeVO grade=null;
			try {
				grade = gservice.getGradeById(user1.getGradeid());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(grade!=null){
				//将用户等级存储进session
				session.setAttribute("grade", grade);
			}else{
				//否则设用户等级为LV0
				try {
					session.setAttribute("grade", gservice.getGradeById(1));
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//设置当前用户到session
			session.setAttribute("user",user1);
			//如果用户设置了记住密码，保持密码在cookie中30天
			if("remember".equalsIgnoreCase(remember)){
				Cookie cookie2=new Cookie("account", user.getAccount());
				Cookie cookie1=new Cookie("password", user.getPassword());
				cookie1.setMaxAge(60*60*24*30);
				cookie2.setMaxAge(60*60*24*30);
				response.addCookie(cookie1);
				response.addCookie(cookie2);
				
			}
		    //跳转到后台主页，返回验证成功的消息给ajax
			jsonResult.setMsg("登陆成功");
			jsonResult.setStatus(1);

			System.out.println(user1+"登陆了");
		    return jsonResult;
		    
		    
		}else{//登录失败
			jsonResult.setMsg("登录失败");
			jsonResult.setStatus(0);
			System.out.println("登陆失败");
			return jsonResult;
		}
	}
	@RequestMapping("/index")
	public String index(HttpSession session,String type){
		List<PicVO> picList = null ;
		if(type==null){
			type = "" ;
		}
		System.out.println(type);
		try {
			picList = pservice.getPicList(type) ;
			session.setAttribute("picList", picList);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "user/index";
	}
	@RequestMapping("/setting")
	public String setting(){
		return "user/setting";
	}
	@RequestMapping("/center")
	public String center(HttpSession session){
		CurrentUser cuser = CurrentUser.getInstance() ;
		int cuid = cuser.getUserId() ;
		List<PicVO> list = null ;
		try {
			list = pservice.getUserPicList(cuid) ;
			session.setAttribute("cuserList", list);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "user/center";
	}
	@RequestMapping("/collect")
	public String collect(){
		return "user/collect";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login.action";
	}
	@RequestMapping("/list")
	public String list(HttpSession session,HttpServletRequest request){
		System.out.println("uri:"+request.getRequestURI());
		return "user/list";
	}
	/*@RequestMapping("/doedit")
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
	}*/
}
