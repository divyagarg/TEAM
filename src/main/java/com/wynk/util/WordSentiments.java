package com.wynk.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
@Component
public class WordSentiments {
	public static Set<String> positiveWords;
	public static Set<String> negativeWords;
	public static Set<String> blacklistWords;
	
	public WordSentiments(){
		loadPositiveWords();
		loadNegativeWords();
		loadBlackListWords();
	}
	
	//We get these values from DB as well
	private void loadPositiveWords(){
		positiveWords = new HashSet<String>();
		positiveWords.add("Happy");
		positiveWords.add("like");
		positiveWords.add("amazing");
		positiveWords.add("good");
		positiveWords.add("love");
		positiveWords.add("agree");
	}
	
	//We get these values from DB as well
	private void loadNegativeWords(){
		negativeWords = new HashSet<String>();
		negativeWords.add("Cry");
		negativeWords.add("hate");
		negativeWords.add("fuck");
		negativeWords.add("kill");
		negativeWords.add("die");
		negativeWords.add("disgusting");
	}
	
	private void loadBlackListWords(){
		blacklistWords = new HashSet<String>();
		blacklistWords.add("Murder");
		blacklistWords.add("ISIS");
		blacklistWords.add("bomb");
		blacklistWords.add("Gun");
	}

	public static Set<String> getPositiveWords() {
		return positiveWords;
	}

	public static Set<String> getNegativeWords() {
		return negativeWords;
	}

	public static Set<String> getBlacklistWords() {
		return blacklistWords;
	}
	
	
}
