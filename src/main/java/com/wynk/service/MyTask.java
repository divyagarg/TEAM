package com.wynk.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wynk.model.TextRequest;
import com.wynk.service.callables.InputSanitizer;
import com.wynk.service.callables.SentimentAnalyzer;
import com.wynk.service.callables.VowelFrequencyCalculator;
import com.wynk.service.callables.WordFrequencyCalculator;

@EnableScheduling
@Component
public class MyTask {
	
	private InputSanitizer inputSantizerCallable = new InputSanitizer();
	private WordFrequencyCalculator wordFrequencyCalculator = new WordFrequencyCalculator();
	private VowelFrequencyCalculator vowelFrequencyCalculator = new VowelFrequencyCalculator();
	private SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
	
	@Scheduled(fixedRate=5000)
	public void checkTextAnalysisRequestPresent() throws InterruptedException{
		if(TeamRequestServiceImpl.inMemoryMap.size()==0){
			return;
		}
		for(Entry<Integer, TextRequest> entry:TeamRequestServiceImpl.inMemoryMap.entrySet()){
			
			if(!entry.getValue().isCompleted()){
				
				inputSantizerCallable.setText(entry.getValue().getInputText());
				inputSantizerCallable.setId(entry.getKey());
				FutureTask<String> task = new FutureTask<String>(inputSantizerCallable);
				Thread t1 = new Thread(task, "Sanitizer");
				t1.start();
				String result = null;
				try {
					result = task.get(); // this will wait for the task to
											// finish, if it hasn't yet.
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (ExecutionException e) {
					e.getCause().printStackTrace(); // e.getCause() holds the
													// exception that happened
													// on the calculation thread
				}
				wordFrequencyCalculator.setText(result);
				wordFrequencyCalculator.setId(entry.getKey());
				FutureTask<Map<String, Integer>> wordFreqTask = new FutureTask<Map<String, Integer>>(wordFrequencyCalculator);
				Thread t2 = new Thread(wordFreqTask, "wordFreqThread");
				t2.start();
				t1.join();
				Map<String, Integer> wordFreqMap= null;
				try{
					wordFreqMap = wordFreqTask.get();
				}catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (ExecutionException e) {
					e.getCause().printStackTrace(); 
				}
				
				vowelFrequencyCalculator.setWordFreqMap(wordFreqMap);
				vowelFrequencyCalculator.setId(entry.getKey());
				FutureTask<Map<Character, Integer>> vowelFreqTask = new FutureTask<Map<Character, Integer>>(vowelFrequencyCalculator);
				Thread t3= new Thread(vowelFreqTask, "vowelFreqThread");
				t3.start();
				t2.join();
				
				sentimentAnalyzer.setWordFrequency(wordFreqMap);
				sentimentAnalyzer.setId(entry.getKey());
				FutureTask<String> sentimentTask = new FutureTask<String>(sentimentAnalyzer);
				Thread t4= new Thread(sentimentTask, "Sentiment Thread");
				t4.start();
				t2.join();
				
				entry.getValue().setCompleted(true);
			}
		}
	}
}
