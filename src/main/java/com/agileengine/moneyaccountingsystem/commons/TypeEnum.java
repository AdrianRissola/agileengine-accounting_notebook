package com.agileengine.moneyaccountingsystem.commons;

public enum TypeEnum {
	
	DEBIT(1,"debit"),
	CREDIT(2,"credit");
	
	private Integer id;
	private String type;

	TypeEnum(Integer id, String type){
		this.setId(id);
		this.setType(type);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
