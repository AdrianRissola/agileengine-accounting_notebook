package com.agileengine.moneyaccountingsystem.exception;

import com.agileengine.moneyaccountingsystem.response.StatusEnum;

public class MoneyAccountException extends Exception {
	
	private StatusEnum statusEnum;
	
	public MoneyAccountException(){
		super();
	}
	
	public MoneyAccountException(StatusEnum statusEnum){
		this.statusEnum = statusEnum;
	}

	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

}
