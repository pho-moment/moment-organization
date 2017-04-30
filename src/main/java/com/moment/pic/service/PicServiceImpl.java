package com.moment.pic.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.domain.JsonResult;
import com.moment.common.util.ConfigUtil;
import com.moment.common.util.SearchConditionUtils;
import com.moment.common.util.SpringUtil;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.pic.dao.PicVOMapper;
import com.moment.pic.domain.PicVO;
import com.moment.pic.domain.PicVOExample;
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
	

}
