package com.wynk.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wynk.enums.RESPONSE_CODES;
import com.wynk.model.TextAnalyzer;
import com.wynk.service.TeamRequestService;

import com.wynk.util.GenericUtil;



@Controller
@RequestMapping("/team/v1")
public class TeamController {
	
	@Autowired
	TeamRequestService teamRequestService;
	@Autowired
    MessageSource messageSource;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody TextAnalyzer getAnalysisResult(@RequestParam(value="id", required=true) Integer id){
		if(teamRequestService.isIdExist(id)){
			return teamRequestService.getTextAnalysisReport(id);
		}
		
		return null;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> analyzeText(@RequestBody String body) throws Exception{
		if(body == null ){
			throw GenericUtil.getErrorObject(RESPONSE_CODES.INPUT_DATA_NEEDED);
		}
		JSONObject requestBody = new JSONObject(body);
		String inputText = requestBody.getString("text");
		if(inputText == null){
			throw GenericUtil.getErrorObject(RESPONSE_CODES.INPUT_DATA_NEEDED);
		}
		Integer id = teamRequestService.addNewRequest(inputText);
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put("id", id);
		return map;
	}
	
	
	
	
}