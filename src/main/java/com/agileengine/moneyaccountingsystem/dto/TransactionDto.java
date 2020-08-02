package com.agileengine.moneyaccountingsystem.dto;

import java.util.Date;

import com.agileengine.moneyaccountingsystem.commons.TypeEnum;

public class TransactionDto extends Dto{
	

    private TypeEnum type;
    private Double amount;
    private Date effectiveDate;
    
	public TransactionDto() {
	}
    
	public TransactionDto(TypeEnum type, Double ammount) {
		this.setType(type);
		this.setAmount(ammount);
	}
	public TypeEnum getType() {
		return type;
	}
	
	public void setType(TypeEnum type) {
		this.type = type;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
    
    

}
