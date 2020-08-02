package com.agileengine.moneyaccountingsystem.response;

public class Status {
	
	private Integer code;
	private String message;
	
	public Status(){}
	
	public Status(StatusEnum statusEnum, String detail){
		this.setCode(statusEnum.getId());
		this.setMessage(statusEnum.getMessage());
	}
	
	public Status(Integer code, String message){
		this.setCode(code);
		this.setMessage(message);
	}
	
	public Status(StatusEnum statusEnum) {
		this.setCode(statusEnum.getId());
		this.setMessage(statusEnum.getMessage());
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
