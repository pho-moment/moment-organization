package com.moment.picCalendar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.domain.CurrentUser;
import com.moment.common.util.SearchConditionUtils;
import com.moment.common.util.TransTimestamp;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.picCalendar.dao.PiccalendarVOMapper;
import com.moment.picCalendar.domain.PiccalendarVO;
import com.moment.picCalendar.domain.PiccalendarVOExample;

@Service
public class CalendarServiceImpl implements CalendarService {
	@Autowired
	private PiccalendarVOMapper mapper;
	@Override
	public int addPiccalendarVO(PiccalendarVO calendar) throws Throwable {
		CurrentUser currentuser=new CurrentUser();
		//先判断今天是否上传日志了，如果未上传，添加日志
		if(getPiccalendarVOByDate(currentuser.getUserId())==null){
			return mapper.insertSelective(calendar);
		}else{
			return 0;
		}
			
	}

	@Override
	public int deletePiccalendarVO(Integer id) throws Throwable {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public PiccalendarVO getPiccalendarVOById(Integer id) throws Throwable {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updatePiccalendarVO(PiccalendarVO calendar) throws Throwable {
		return mapper.updateByPrimaryKeySelective(calendar);
	}

	@Override
	public DataTablesResponse<PiccalendarVO> list(DataTablesRequest request) throws Throwable {
		PiccalendarVOExample example = new PiccalendarVOExample();
		DataTablesResponse<PiccalendarVO> response = new DataTablesResponse<PiccalendarVO>();
		SearchConditionUtils.wrapperAndCondition(example, request);// 封装查询条件
		response.setDraw(request.getDraw());
		response.setRecordsTotal(mapper.countByExample(example));
		response.setData(mapper.selectByExample(example));
		return response;
	}
	//获取用户今天上传的摄影日志
	@Override
	public PiccalendarVO getPiccalendarVOByDate(Integer id) throws Throwable {
		//获取当前时间，并将时间数设为00：00：00
		Date date1=new Date();
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		//获取时间，并将时间数设为00：00：00
		TransTimestamp ts=new TransTimestamp();
		Date date2=ts.getTomorrow(date1);
		PiccalendarVOExample example = new PiccalendarVOExample();
		//获取在这时间范围内的图片,可以使用链式结构
		example.createCriteria().andTimeBetween(date1, date2).andIdEqualTo(id);
		List<PiccalendarVO> list = mapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
