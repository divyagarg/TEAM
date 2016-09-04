package com.wynk.service;

import java.util.List;

import com.wynk.model.TextAnalyzer;
import com.wynk.model.TextRequest;

public interface TeamRequestService {

	Integer addNewRequest(String inputText);
	
	TextAnalyzer getTextAnalysisReport(Integer id);
	
	public List<TextRequest> getAllPendingRequest();

	public boolean isIdExist(Integer id);
}
