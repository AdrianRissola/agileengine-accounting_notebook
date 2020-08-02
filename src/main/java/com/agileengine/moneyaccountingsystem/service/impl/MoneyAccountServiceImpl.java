package com.agileengine.moneyaccountingsystem.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agileengine.moneyaccountingsystem.commons.Utils;
import com.agileengine.moneyaccountingsystem.dto.DtoConverter;
import com.agileengine.moneyaccountingsystem.dto.TransactionDto;
import com.agileengine.moneyaccountingsystem.entity.Account;
import com.agileengine.moneyaccountingsystem.entity.Transaction;
import com.agileengine.moneyaccountingsystem.exception.MoneyAccountException;
import com.agileengine.moneyaccountingsystem.repository.AccountRepository;
import com.agileengine.moneyaccountingsystem.repository.TransactionRepository;
import com.agileengine.moneyaccountingsystem.service.MoneyAccountService;


@Transactional
@Service("moneyAccountService")
public class MoneyAccountServiceImpl implements MoneyAccountService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	

	@Override
	public List<TransactionDto> getTransactions() {
		return DtoConverter.toDto(this.transactionRepository.findAll());
	}

	@Override
	public TransactionDto commitTransaction(TransactionDto newTx) throws MoneyAccountException {
		Account account = this.accountRepository.findAll().get(0);
		Utils.validateOperation(newTx, account);
		account.exectue(newTx);
		this.accountRepository.save(account);
		Transaction tx = DtoConverter.fromDto(newTx);
		tx.setEffectiveDate(new Date());
		Transaction commitedTx = this.transactionRepository.save(tx);
		return DtoConverter.toDto(commitedTx);
	}



	@Override
	public TransactionDto findTransactionById(String id) throws MoneyAccountException {
		Transaction tx = this.transactionRepository.findById(new Long(id)).orElse(null);
		Utils.validateNotNull(tx);
		return DtoConverter.toDto(tx);
	}

	@Override
	public Double getBalance() {
		return this.accountRepository.findAll().get(0).getBalance();
	}



}
