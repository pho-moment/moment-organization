package com.moment.pic.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.moment.common.domain.CurrentUser;
import com.moment.common.domain.JsonResult;
import com.moment.grade.domain.GradeVO;
import com.moment.pic.domain.PicEX;
import com.moment.pic.domain.PicVO;
import com.moment.pic.service.PicService;
import com.moment.user.domain.UserVO;
import com.moment.user.service.UserService;
import com.qiniu.http.Response;

@Controller
@RequestMapping("/pic")
public class PicController {
	@Autowired
	private PicService service;

	@Autowired
	private UserService userservice;

	@RequestMapping("/upload")
	public String upload() {
		return "pic/upload";
	}

	@Transactional
	@RequestMapping("/doupload")
	public @ResponseBody JsonResult doUpload(MultipartFile file, PicVO pic,HttpSession session) throws Throwable {
		CurrentUser cuser = CurrentUser.getInstance();
		service.updateUserGrade(cuser.getCurrentUser());
		GradeVO grade = cuser.getGrade();
		JsonResult result = new JsonResult();
		int test = service.getPicnumByDate(cuser.getUserId()) ;
		int test2 = grade.getUploadnum() ;
		if (service.getPicnumByDate(cuser.getUserId()) < grade.getUploadnum()) {
			byte[] b = file.getBytes();
			pic.setUserid(cuser.getUserId());
			service.doUpload(b, pic);

			result.setMsg("上传成功");
			result.setStatus(1);
			UserVO user = userservice.getUserById(cuser.getUserId());
			user.setPicnum(user.getPicnum() + 1);
			// 用户数据表中的上传图片数量改变
			userservice.updateUser(user);
			
			/*List<PicVO> picList = (List<PicVO>) session.getAttribute("picList") ;
			picList.add(0, pic);
			
			List<PicVO> cuserList = (List<PicVO>) session.getAttribute("cuserList") ;
			cuserList.add(0,pic);*/
			return result;

		} else {
			result.setMsg("你今天的上传数量已经全部用完");
			result.setStatus(0);
			return result;
		}
	}

	@RequestMapping("/delete")
	public @ResponseBody JsonResult delete(Integer id) {
		JsonResult result = new JsonResult();
		try {
			int i = service.deletePicFromQiniu(id);
			result.setStatus(i);
			result.setMsg("删除成功");
		} catch (Throwable e) {
			result.setStatus(0);
			result.setMsg("删除失败");
			return result;
		}
		return result;
	}

	@RequestMapping("/doEdit")
	public String doEdit(PicVO pic) {
		try {
			service.updatePicVO(pic);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping("/getPicList")
	public  @ResponseBody List<PicEX> getPicList(String type){
		List<PicEX> picList = null ;
		if(type==null){
			type = "" ;
		}
		System.out.println(type);
		try {
			picList = service.getPicList(type) ;
			System.out.println(picList);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return picList;
	}
}
