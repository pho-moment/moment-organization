package com.moment.picCalendar.service;

import java.util.Date;

import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.picCalendar.domain.PiccalendarVO;

public interface CalendarService {
	public int addPiccalendarVO(PiccalendarVO calendar) throws Throwable;
	
	public int deletePiccalendarVO(Integer id) throws Throwable;
	
	public PiccalendarVO getPiccalendarVOById(Integer id) throws Throwable;
	
	
	public PiccalendarVO getPiccalendarVOByDate(Integer id) throws Throwable;
	
	public int updatePiccalendarVO(PiccalendarVO calendar) throws Throwable;
	
	/**
	 * 使用datatables的分页查询
	 * @param request
	 * @return
	 */
	public DataTablesResponse<PiccalendarVO> list(DataTablesRequest request) throws Throwable;
}
