package com.moment.pic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.util.SearchConditionUtils;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.pic.dao.PicVOMapper;
import com.moment.pic.domain.PicVO;
import com.moment.pic.domain.PicVOExample;
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

}
