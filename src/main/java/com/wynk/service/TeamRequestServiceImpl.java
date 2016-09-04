package com.wynk.service;
import com.wynk.enums.TEAM_STATUS;
import com.wynk.model.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class TeamRequestServiceImpl implements TeamRequestService {
	
	private static final Object Id = null;
	public static Map<Integer, TextRequest> inMemoryMap = new ConcurrentHashMap<Integer, TextRequest>();
	public static Map<Integer, TextAnalyzer> outputMap = new ConcurrentHashMap<Integer, TextAnalyzer>();
	private static volatile Integer counter=1;
	
	TeamRequestServiceImpl(){
		super();
	}
	
	@Override
	public List<TextRequest> getAllPendingRequest(){
		return null;
//		return teamequestDao.getAllPendingRequest();
	}


	@Override
	public Integer addNewRequest(String inputText) {
		int id = init_request(inputText);
		init_analyze_result(id, inputText);
		counter++;
		return id;
	}

	private int init_request(String inputText) {
		TextRequest tr = new TextRequest();
		tr.setId(counter);
		tr.setInputText(inputText);
		tr.setCompleted(false);
		//Keep it in map
		inMemoryMap.put(counter, tr);
		return tr.getId();
		
	}

	private void init_analyze_result(int id, String inputText) {
		TextAnalyzer analyzer = new TextAnalyzer();
		analyzer.setId(id);
		analyzer.setInputText(inputText);
		analyzer.setStartTime(new Date());
		analyzer.setStatus(TEAM_STATUS.IN_PROGRESS);
		analyzer.setCompletedTask("0/4");
		outputMap.put(id, analyzer);
		
	}

	@Override
	public TextAnalyzer getTextAnalysisReport(Integer id) {
		return outputMap.get(id);
	}
	
	@Override
	public boolean isIdExist(Integer id){
		if(outputMap.get(id) == null){
			return false;
		}else{
			return true;
		}
	}
	
	
	
	
}
