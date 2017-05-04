package com.moment.pic.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.moment.common.domain.CurrentUser;
import com.moment.grade.domain.GradeVO;
import com.moment.pic.domain.PicVO;
import com.moment.pic.service.PicService;
import com.moment.user.domain.UserVO;
import com.moment.user.service.UserService;
@Controller
@RequestMapping("/pic")
public class PicController {
	@Autowired
	private PicService service ;
	@Autowired
	private UserService userservice ;
	
	@RequestMapping("/upload")
	public String upload() {
		return "pic/upload";
	}
	
	@Transactional
	@RequestMapping("/doupload")
	public String doUpload(MultipartFile file,PicVO pic) throws Throwable {
		CurrentUser cuser = CurrentUser.getInstance() ;
		//获得用户的等级
		GradeVO grade=cuser.getGrade();
		//判断今天用户上传的图片数量是否达到上限
		if(service.getPicnumByDate(cuser.getUserId())<grade.getUploadnum()){
			pic.setUserid(cuser.getUserId());
			pic.setName("test");
			byte[] b = file.getBytes() ;
			service.doUpload(b, pic);
			UserVO user=userservice.getUserById(cuser.getUserId());
			user.setPicnum(user.getPicnum()+1);
			//用户数据表中的上传图片数量改变
			userservice.updateUser(user);
		}
		
		return "redirect:"+pic.getPicpath();
	}
}
