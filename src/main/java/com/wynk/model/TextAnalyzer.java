package com.wynk.model;

import java.util.Date;
import java.util.Map;

import com.wynk.enums.TEAM_STATUS;

public class TextAnalyzer{
	
	private int id;
	private Date startTime;
	private Date endTime;
	private TEAM_STATUS status;
	private Map<String, Integer> wordFreq;
	private Map<Character, Integer> vowelFreq;
	private String sentiment;
	private String completedTask;
	private String inputText;
	private String santizedString;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public TEAM_STATUS getStatus() {
		return status;
	}
	public void setStatus(TEAM_STATUS status) {
		this.status = status;
	}
	
	public Map<String, Integer> getWordFreq() {
		return wordFreq;
	}
	public void setWordFreq(Map<String, Integer> wordFreq) {
		this.wordFreq = wordFreq;
	}
	public Map<Character, Integer> getVowelFreq() {
		return vowelFreq;
	}
	public void setVowelFreq(Map<Character, Integer> vowelFreq) {
		this.vowelFreq = vowelFreq;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public String getCompletedTask() {
		return completedTask;
	}
	public void setCompletedTask(String completedTask) {
		this.completedTask = completedTask;
	}
	public String getInputText() {
		return inputText;
	}
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
	public String getSantizedString() {
		return santizedString;
	}
	public void setSantizedString(String santizedString) {
		this.santizedString = santizedString;
	}
	
	
}
