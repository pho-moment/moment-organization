package com.moment.pic.service;


import java.io.File;

import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.pic.domain.PicVO;
import com.qiniu.http.Response;

public interface PicService {
	public int addPic(PicVO pic) throws Throwable;
	
	public int deletePic(Integer id) throws Throwable;
	
	public PicVO getPicById(Integer id) throws Throwable;
	
	public int updatePicVO(PicVO pic) throws Throwable;
	public DataTablesResponse<PicVO> list(DataTablesRequest request) throws Throwable;
	public  Response doUpload(byte[] b,PicVO pic) throws Throwable ;

	public int getPicnumByDate(Integer id) throws Throwable;

	
}
