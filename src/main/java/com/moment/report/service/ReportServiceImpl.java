package com.moment.report.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.util.MD5Util;
import com.moment.common.util.SearchConditionUtils;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.grade.domain.GradeVO;
import com.moment.grade.domain.GradeVOExample;
import com.moment.report.dao.ReportVOMapper;
import com.moment.report.domain.ReportVO;
import com.moment.report.domain.ReportVOExample;
@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportVOMapper mapper;
	@Override
	public int addReport(ReportVO report) throws Throwable {
		return mapper.insertSelective(report);
	}

	@Override
	public int deleteReport(Integer id) throws Throwable {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public ReportVO getReportById(Integer id) throws Throwable {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateReport(ReportVO report) throws Throwable {
		return mapper.updateByPrimaryKeySelective(report);
	}

	@Override
	public DataTablesResponse<ReportVO> list(DataTablesRequest request) throws Throwable {
		ReportVOExample example = new ReportVOExample();
		DataTablesResponse<ReportVO> response = new DataTablesResponse<ReportVO>();
		SearchConditionUtils.wrapperAndCondition(example, request);// 封装查询条件
		response.setDraw(request.getDraw());
		response.setRecordsTotal(mapper.countByExample(example));
		response.setData(mapper.selectByExample(example));
		return response;
	}

}
