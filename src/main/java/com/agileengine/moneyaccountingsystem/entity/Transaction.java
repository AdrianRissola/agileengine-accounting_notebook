package com.agileengine.moneyaccountingsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;

import com.agileengine.moneyaccountingsystem.commons.TypeEnum;

@Entity
@Table(name = "TRANSACTION")
public class Transaction extends AbstractEntity{
	
	public Transaction() {
	}
	
	public Transaction(TypeEnum type, Double amount, Date date) {
		this.setType(type);
		this.setAmount(amount);
		this.setEffectiveDate(date);
	}
	
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long transactionId;

	@Column(name = "TYPE", nullable = false)
    private TypeEnum type;
	
	@Column(name = "AMOUNT", nullable = false)
    private Double amount;
	
	@Column(name = "EFFECTIVE_DATE", nullable = false)
    private Date effectiveDate;
	
	@Version
	private Long version;
	

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
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
