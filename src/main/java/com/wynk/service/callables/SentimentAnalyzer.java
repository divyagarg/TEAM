package com.wynk.service.callables;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wynk.model.TextAnalyzer;
import com.wynk.service.TeamRequestServiceImpl;
import com.wynk.util.WordSentiments;

@Component
public class SentimentAnalyzer implements Callable<String> {
	
	private Integer id;
	Map<String, Integer> wordFrequency;
	@Autowired
	WordSentiments wordSentiments;
	int rank;
	public SentimentAnalyzer(){
		
	}
	public SentimentAnalyzer(Map<String, Integer> wordFrequency) {
		this.wordFrequency = wordFrequency;
	}
	
	@Override
	public String call() throws Exception {
		System.out.println("Inside Sentiment Analyzer: "+ wordFrequency);
		System.out.println(Thread.currentThread().getName());
		String sentiment = getSentiment();
		
		TextAnalyzer analyzer = TeamRequestServiceImpl.outputMap.get(id);
		if(analyzer!=null){
			analyzer.setCompletedTask("4/4");
			analyzer.setEndTime(new Date());
			analyzer.setSentiment(sentiment);
		}
		return sentiment;
	}
	private String getSentiment() {
		String sentiment = "";
		for(Map.Entry<String, Integer> en: wordFrequency.entrySet()){
			if(wordSentiments.getPositiveWords().contains(en.getKey())){
				rank = rank+en.getValue();
			}
			if(wordSentiments.getNegativeWords().contains(en.getKey())){
				rank = rank-en.getValue();
			}
			
		}
		if(rank>2){
			sentiment= "Positive";
		}else if(rank<-2){
			sentiment= "Negative";
		}else{
			sentiment= "neutral";
		}
		//Update in DB as well mark completed task as 4/4
		System.out.println("Exiting Sentiment Analyzer: "+ sentiment);
		return sentiment;
	}
	public void setWordFrequency(Map<String, Integer> wordFrequency) {
		this.wordFrequency = wordFrequency;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
