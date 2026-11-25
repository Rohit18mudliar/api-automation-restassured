package com.api.models.response;

public class DeleteBookingResponse{
	private boolean success;
	private String message;

	public DeleteBookingResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public DeleteBookingResponse() {
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}
