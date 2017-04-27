package com.moment.picCalendar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moment.common.domain.CurrentUser;
import com.moment.common.domain.JsonResult;
import com.moment.picCalendar.domain.PiccalendarVO;
import com.moment.picCalendar.service.CalendarService;


@Controller
@RequestMapping("/canlendar")
public class PicCalendarController {
	@Autowired
	private CalendarService service;
	
	/**
	 * 前台操作添加摄影日志，返回结果到前端
	 * */
	@RequestMapping("/add")
	public @ResponseBody JsonResult add(@Valid PiccalendarVO calendar,BindingResult bindingResult,JsonResult jsonResult) throws Exception{
		
		int flag=0;
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			for(ObjectError objErr:allErrors){
				jsonResult.setMsg("添加日志失败");
			}
			System.out.println(bindingResult.getFieldErrorCount());
			jsonResult.setStatus(0);
		}else{
			try {
				//获取当前用户的id
				CurrentUser currentuser=new CurrentUser();
				calendar.setUserid(currentuser.getUserId());
				flag = service.addPiccalendarVO(calendar);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(flag!=0){
			jsonResult.setMsg("添加日志成功");
			jsonResult.setStatus(1);
		} else{
			jsonResult.setMsg("添加日志失败");
			jsonResult.setStatus(0);
		}
		return jsonResult;
	}
	/**
	 * 前台操作添加摄影日志，返回结果到前端
	 * */
	@RequestMapping("/update")
	public @ResponseBody JsonResult update(@Valid PiccalendarVO calendar,BindingResult bindingResult,JsonResult jsonResult) throws Exception{
		
		int flag=0;
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			for(ObjectError objErr:allErrors){
				jsonResult.setMsg("修改日志失败");
			}
			System.out.println(bindingResult.getFieldErrorCount());
			jsonResult.setStatus(0);
		}else{
			try {
				flag = service.updatePiccalendarVO(calendar);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(flag!=0){
			jsonResult.setMsg("修改日志成功");
			jsonResult.setStatus(1);
		} else{
			jsonResult.setMsg("修改日志失败");
			jsonResult.setStatus(0);
		}
		return jsonResult;
	}
	/**
	 * 删除日志
	 * */
	@RequestMapping("/doDel")
	public @ResponseBody JsonResult doDel(Integer id) throws Throwable{
		JsonResult rs=new JsonResult();
		service.deletePiccalendarVO(id);
		rs.setStatus(1);
		rs.setMsg("删除成功！");
		return rs;
	}
}
