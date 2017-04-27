package com.moment.pic.service;

import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.pic.domain.PicVO;

public interface PicService {
	public int addPic(PicVO pic) throws Throwable;
	
	public int deletePic(Integer id) throws Throwable;
	
	public PicVO getPicById(Integer id) throws Throwable;
	
	public int updatePicVO(PicVO pic) throws Throwable;
	public DataTablesResponse<PicVO> list(DataTablesRequest request) throws Throwable;
}
