package com.agileengine.moneyaccountingsystem.service;

import java.util.List;

import com.agileengine.moneyaccountingsystem.dto.TransactionDto;
import com.agileengine.moneyaccountingsystem.exception.MoneyAccountException;

public interface MoneyAccountService {
	
	public List<TransactionDto> getTransactions();

	public TransactionDto commitTransaction(TransactionDto newTx) throws MoneyAccountException;

	public TransactionDto findTransactionById(String id) throws MoneyAccountException;

	public Double getBalance();

}
