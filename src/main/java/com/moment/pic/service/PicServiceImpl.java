package com.moment.pic.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.domain.JsonResult;
import com.moment.common.util.ConfigUtil;
import com.moment.common.util.SearchConditionUtils;
import com.moment.common.util.SpringUtil;
import com.moment.common.util.TransTimestamp;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.pic.dao.PicVOMapper;
import com.moment.pic.domain.PicVO;
import com.moment.pic.domain.PicVOExample;
import com.moment.picCalendar.domain.PiccalendarVO;
import com.moment.picCalendar.domain.PiccalendarVOExample;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
@Service
public class PicServiceImpl implements PicService{
	@Autowired
	private PicVOMapper mapper;
	@Override
	public int addPic(PicVO pic) throws Throwable {
		return mapper.insertSelective(pic);
	}

	@Override
	public int deletePic(Integer id) throws Throwable {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public PicVO getPicById(Integer id) throws Throwable {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updatePicVO(PicVO pic) throws Throwable {
		return mapper.updateByPrimaryKeySelective(pic);
	}

	@Override
	public DataTablesResponse<PicVO> list(DataTablesRequest request) throws Throwable {
		PicVOExample example = new PicVOExample();
		DataTablesResponse<PicVO> response = new DataTablesResponse<PicVO>();
		SearchConditionUtils.wrapperAndCondition(example, request);// 封装查询条件
		response.setDraw(request.getDraw());
		response.setRecordsTotal(mapper.countByExample(example));
		response.setData(mapper.selectByExample(example));
		return response;
	}

	@Override
	public Response doUpload(byte[] b, PicVO pic) throws Throwable {
		String bucketname = "moment" ;
		String fileName = UUID.randomUUID().toString() ;
		pic.setPicpath(ConfigUtil.getValue("uri")+fileName);
		/*pic.setName("test");
		pic.setUserid(01);*/
		Auth auth = Auth.create(ConfigUtil.getValue("AccessKey"), ConfigUtil.getValue("SecretKey")) ;
		String token = auth.uploadToken(bucketname);
		UploadManager manager = new UploadManager();  
		Response response = manager.put(b, fileName, token);
		if(response.isOK()){
			mapper.insertSelective(pic) ;
		}
		return response;
		
	}

	//获取用户今天上传的图片数量
			@Override
			public int getPicnumByDate(Integer id) throws Throwable {
				//获取当前时间，并将时间数设为00：00：00
				Date date1=new Date();
				date1.setHours(0);
				date1.setMinutes(0);
				date1.setSeconds(0);
				//获取时间，并将时间数设为00：00：00
				TransTimestamp ts=new TransTimestamp();
				Date date2=ts.getTomorrow(date1);
				PicVOExample example = new PicVOExample();
				//获取在这时间范围内的图片,可以使用链式结构
				example.createCriteria().andTimeBetween(date1, date2).andIdEqualTo(id);
				List<PicVO> list = mapper.selectByExample(example);
				return list.size();
			}

	

}
