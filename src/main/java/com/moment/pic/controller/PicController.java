package com.moment.pic.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.moment.common.domain.CurrentUser;
import com.moment.pic.domain.PicVO;
import com.moment.pic.service.PicService;
@Controller
@RequestMapping("/pic")
public class PicController {
	@Autowired
	private PicService service ;
	
	
	@RequestMapping("/upload")
	public String upload() {
		return "pic/upload";
	}

	@RequestMapping("/doupload")
	public String doUpload(MultipartFile file,PicVO pic) throws Throwable {
		CurrentUser user = CurrentUser.getInstance() ;
		pic.setUserid(user.getUserId());
		pic.setName("test");
		byte[] b = file.getBytes() ;
		service.doUpload(b, pic);
		return "redirect:"+pic.getPicpath();
	}
}
