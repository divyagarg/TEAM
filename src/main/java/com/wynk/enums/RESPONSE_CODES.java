package com.wynk.enums;

public enum RESPONSE_CODES {
	
	FAIL (1000, "FAIL" , "Oops! Something went wrong with your wallet. Please give it a try again!"),

	INCORRECT_JSON_FORMAT (1001, "The json request seems to be malformed.", "Oops! Something went wrong with your wallet. Please give it a try again!"),
	INPUT_DATA_NEEDED(1002, "Data is missing in the request", "Data Invalid"),
	SUCCESS(2000, "SUCCESS", "Transaction successful!");
	
	private final int code;
	private final String desc;
	private final String message;
	
	private RESPONSE_CODES(int code, String description , String message) {
		this.code = code;
		this.desc = description;
		this.message = message;
	}
	
	public String getDescription() {
		return desc;
	}

	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return code + ": " + desc;
	}

	
	
}
