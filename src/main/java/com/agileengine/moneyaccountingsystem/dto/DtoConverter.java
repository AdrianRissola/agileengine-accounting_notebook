package com.agileengine.moneyaccountingsystem.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.agileengine.moneyaccountingsystem.entity.Transaction;

public class DtoConverter {

	public static Transaction fromDto(TransactionDto newTx) {
		Transaction tx = new Transaction();
		BeanUtils.copyProperties(newTx, tx);
		tx.setAmount(new Double(newTx.getAmount()));
		return tx;
	}

	public static TransactionDto toDto(Transaction tx) {
		TransactionDto txDto = new TransactionDto();
		BeanUtils.copyProperties(tx, txDto);
		txDto.setId(tx.getTransactionId().toString());
		return txDto;
	}

	public static List<TransactionDto> toDto(List<Transaction> txs) {
		List<TransactionDto> txsDto = new ArrayList<>();
		txs.forEach(tx -> txsDto.add(toDto(tx)));
		return txsDto;
	}

}
