package com.wynk.service.callables;


import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import com.wynk.model.TextAnalyzer;
import com.wynk.service.TeamRequestServiceImpl;

public class VowelFrequencyCalculator implements Callable<Map<Character, Integer>>{

	Map<String, Integer> wordFreqMap;
	Map<Character, Integer> vowelFreqMap = new ConcurrentHashMap<Character, Integer>();
	char[] vowels= {'a', 'e', 'i', 'o', 'u'};
	private Integer id;
	

	public VowelFrequencyCalculator() {
		super();
	}

	public VowelFrequencyCalculator(Map<String, Integer> wordFreqMap) {
		super();
		this.wordFreqMap = wordFreqMap;
	}


	@Override
	public Map<Character, Integer> call() throws Exception {
		System.out.println("Inside Vowel: "+ wordFreqMap);
		System.out.println("Current thread is :"+ Thread.currentThread().getName());
		
		for(Entry<String, Integer> entry : wordFreqMap.entrySet()){
			String word = entry.getKey();
			for(int i=0;i<vowels.length;i++){
				Integer freq = vowelFreqMap.get(vowels[i]);
				if(word.indexOf(vowels[i])>=0){
					if(freq == null){
						vowelFreqMap.put(vowels[i], entry.getValue());
					}	
					else{
						vowelFreqMap.put(vowels[i], freq+entry.getValue());
					}
				}
			}
		}
		
		//Update Db with vowels frequency as well update the completed task as 3/3
		System.out.println("Exiting Vowel: "+ vowelFreqMap);
		
		TextAnalyzer analyzer = TeamRequestServiceImpl.outputMap.get(id);
		if(analyzer!=null){
			analyzer.setCompletedTask("3/4");
			analyzer.setVowelFreq(vowelFreqMap);
		}
		return vowelFreqMap;
		
	}

	public Map<String, Integer> getWordFreqMap() {
		return wordFreqMap;
	}

	public void setWordFreqMap(Map<String, Integer> wordFreqMap) {
		this.wordFreqMap = wordFreqMap;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

}
