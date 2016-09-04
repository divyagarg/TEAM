package com.wynk.service.callables;

import java.util.StringTokenizer;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wynk.model.TextAnalyzer;
import com.wynk.service.TeamRequestServiceImpl;
import com.wynk.util.WordSentiments;

@Component
public class InputSanitizer implements Callable<String>{
	private Integer id;
	private String text;
	@Autowired
	WordSentiments wordSentiments;
	public InputSanitizer() {
		super();
	}
	
	public InputSanitizer(String text) {
		super();
		this.text = text;
	}

	

	@Override
	public String call() throws Exception {
		System.out.println("Inside Input santizer: "+ text);
		System.out.println("Current Thread: "+ Thread.currentThread().getName());
		
		String outputText = remove_blacklisted_words(text);
		
		TextAnalyzer analyzer = TeamRequestServiceImpl.outputMap.get(id);
		if(analyzer!=null){
			analyzer.setCompletedTask("1/4");
			analyzer.setSantizedString(outputText);
		}
		
		return outputText;
	}
	
	private String remove_blacklisted_words(String text2) {
		StringBuilder processedText = new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(text, " ");
		while(tokens.hasMoreTokens()){
			String word = tokens.nextToken();
			if(!wordSentiments.getBlacklistWords().contains(word)){
				processedText.append(word+" ");
			}
		}
		processedText.trimToSize();
		System.out.println("Exiting Input santizer: "+ processedText);
		return processedText.toString();
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
