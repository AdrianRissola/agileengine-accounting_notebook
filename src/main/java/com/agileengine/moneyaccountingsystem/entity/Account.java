package com.agileengine.moneyaccountingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.agileengine.moneyaccountingsystem.commons.TypeEnum;
import com.agileengine.moneyaccountingsystem.dto.TransactionDto;

@Entity
@Table(name = "ACCOUNT")
public class Account extends AbstractEntity{
	
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long accountId;
	
	@Column(name = "BALANCE", nullable = false)
    private Double balance;
	
	public Account(){}
	
	public Account(Double balance){
		this.setBalance(balance);
	}
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void exectue(TransactionDto newTx) {
		this.setBalance(this.getBalance() + this.operation(newTx));
	}

	private Double operation(TransactionDto newTx) {
		return newTx.getType().equals(TypeEnum.DEBIT) ? newTx.getAmount() : newTx.getAmount()*-1;
	}
	
	

}
