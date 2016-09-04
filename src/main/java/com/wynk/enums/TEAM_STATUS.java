package com.wynk.enums;

public enum TEAM_STATUS {
	
	IN_PROGRESS("in progress"),
	COMPLETED("completed");
	private final String status;
	
	private TEAM_STATUS(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
}
