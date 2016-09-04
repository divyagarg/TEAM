package com.wynk.service.callables;


import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import com.wynk.model.TextAnalyzer;
import com.wynk.service.TeamRequestServiceImpl;

public class WordFrequencyCalculator implements Callable<Map<String, Integer>>{
	private String text;
	private Integer id;
	Map<String, Integer> wordFreqMap = new ConcurrentHashMap<String, Integer>();
	public WordFrequencyCalculator(){
		
	}

	public WordFrequencyCalculator(String text) {
		super();
		this.text = text;
	}
	
	@Override
	public Map<String, Integer> call() throws Exception {
		
		System.out.println("Inside Word freq: "+ text);
		System.out.println(Thread.currentThread().getName());
		StringTokenizer tokens = new StringTokenizer(text, " ");
		
		while(tokens.hasMoreTokens()){
			String word = tokens.nextToken();
			if(wordFreqMap.get(word) !=null){
				Integer currentFreq = wordFreqMap.get(word);
				wordFreqMap.put(word, currentFreq+1);
			}else{
				wordFreqMap.put(word, 1);
			}
		}
		
		//Update Db with word frequency as well update the completed task as 2/2
		System.out.println("Exiting Word freq: "+ wordFreqMap);
		
		TextAnalyzer analyzer = TeamRequestServiceImpl.outputMap.get(id);
		if(analyzer!=null){
			analyzer.setCompletedTask("2/4");
			analyzer.setWordFreq(wordFreqMap);
		}
		return wordFreqMap;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
