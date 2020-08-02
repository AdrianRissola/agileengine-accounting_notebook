package com.agileengine.moneyaccountingsystem.response;

public enum StatusEnum {
	
	SUCCESSFUL_OPERATION(1, "successful operation"),
	TRANSACTION_STORED(2,"transaction stored"),
	ERROR_INVALID_ID_SUPPLIED(3, "Error: invalid ID supplied"),
	ERROR_INVALID_STATUS_VALUE(4, "Error: invalid status value"),
	ERROR_TRANSACTION_NOT_FOUND(5, "Error: transaction not found"),
	ERROR_INVALID_INPUT(6, "Error: invalid input"),
	ERROR_NEGATIVE_AMOUNT(7, "Error: negative amount"),
	INSUFFICIENT_FUNDS(8, "Error: insufficient funds"),
	OPERATION_TEMPORARILY_UNAVAILABLE(9, "Error: operation temporarily unavailable");
	

	private Integer id;
	private String message;

	StatusEnum(Integer id, String message){
		this.setId(id);
		this.setMessage(message);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
