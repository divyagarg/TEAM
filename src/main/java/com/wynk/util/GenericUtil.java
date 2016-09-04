package com.wynk.util;

import com.wynk.enums.RESPONSE_CODES;

public class GenericUtil {
	
	public static ResponseObject getSuccessResponseObject(Object obj) {
		ResponseObject responseObject = new ResponseObject();
		responseObject.setData(obj);
		responseObject.setStatus(RESPONSE_CODES.SUCCESS.getDescription());
		responseObject.setStatusCode(RESPONSE_CODES.SUCCESS.getCode());
		return responseObject;
	}
	
	public static ResponseObject getFailureResponseObject(Object obj, String message, int failureCode, String desc) {
		
		ResponseObject responseObject = new ResponseObject();
		
		responseObject.setData(obj);
		responseObject.setStatus(RESPONSE_CODES.FAIL.getDescription());
		responseObject.setStatusCode(failureCode);
		responseObject.setMessage(message);
		responseObject.setDescription(desc);
		return responseObject;
	}
	
	public static GenericException getErrorObject(RESPONSE_CODES respoCode) {
		
		GenericException exception = new GenericException();
		exception.setMessage(respoCode.getDescription());
		exception.setStatus(RESPONSE_CODES.FAIL.getDescription());
		exception.setStatusCode(respoCode.getCode());
		exception.setDescription(respoCode.getMessage());
		
		return exception;
	}
}
