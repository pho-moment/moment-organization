package com.moment.report.service;

import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.report.domain.ReportVO;

public interface ReportService {
public int addReport(ReportVO report) throws Throwable;
	
	public int deleteReport(Integer id) throws Throwable;
	
	public ReportVO getReportById(Integer id) throws Throwable;
	
	public int updateReport(ReportVO report) throws Throwable;
	public DataTablesResponse<ReportVO> list(DataTablesRequest request) throws Throwable;
}
