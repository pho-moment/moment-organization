package com.moment.report.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.moment.common.domain.CurrentUser;
import com.moment.common.domain.JsonResult;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.report.domain.ReportVO;
import com.moment.report.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private ReportService service;
	/**
	 * 前台操作添加举报记录，返回json结果到前台页面
	 * */
	@RequestMapping("/add")
	public @ResponseBody JsonResult add(@Valid ReportVO report,BindingResult bindingResult,JsonResult jsonResult,int picid) throws Exception{
		
		int flag=0;
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			for(ObjectError objErr:allErrors){
				jsonResult.setMsg("举报失败");
			}
			System.out.println(bindingResult.getFieldErrorCount());
			jsonResult.setStatus(0);
		}else{
			try {
				//获取当前用户的id
				CurrentUser currentuser=new CurrentUser();
				report.setUserid(currentuser.getUserId());
				report.setPicid(picid);
				flag = service.addReport(report);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(flag!=0){
			jsonResult.setMsg("举报成功");
			jsonResult.setStatus(1);
		} else{
			jsonResult.setMsg("举报失败");
			jsonResult.setStatus(0);
		}
		return jsonResult;
	}
	
	//以下是后台操作
	/**
	 * 列表显示
	 * */
	@RequestMapping("/list")
	public String list(HttpSession session,HttpServletRequest request){
		return "report/list";
	}
	/**
	 * 列表查询结果显示
	 * */
	@RequestMapping("/rest/doSearch")
	public @ResponseBody DataTablesResponse<ReportVO> pageSearch(
			@RequestBody DataTablesRequest request) throws Throwable{
		return service.list(request);
	}
	/**
	 * 编辑权限管理，可添加可修改
	 * */
	@RequestMapping("/edit")
	public ModelAndView add(Integer id) throws Throwable{
		ModelAndView model=new ModelAndView("report/edit");
		if(id!=null&&id!=0){//修改
			model.addObject("permission",service.getReportById(id));
		}
		return model;
	}
	/**
	 * 保存修改
	 * */
	@RequestMapping("/doEdit")
	public ModelAndView doAdd(@Valid ReportVO report,BindingResult bindingResult) throws Throwable{
		ModelAndView model=new ModelAndView("report/list");
		if(report.getId()!=null&&service.getReportById(report.getId())!=null){//修改
			service.updateReport(report);
		}else{//新增
			if(bindingResult.hasErrors()){
				List<ObjectError> allErrors=bindingResult.getAllErrors();
				for(ObjectError objErr:allErrors){
					System.out.println(objErr.getDefaultMessage());
				}
				System.out.println(bindingResult.getFieldErrorCount());
			}else{
				try {
					service.addReport(report);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return model;
	}
	/**
	 * 删除权限
	 * */
	@RequestMapping("/doDel")
	public @ResponseBody JsonResult doDel(Integer id) throws Throwable{
		JsonResult rs=new JsonResult();
		service.deleteReport(id);
		rs.setStatus(1);
		rs.setMsg("删除成功！");
		return rs;
	}
}
