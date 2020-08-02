package com.agileengine.moneyaccountingsystem.commons;

import com.agileengine.moneyaccountingsystem.dto.TransactionDto;
import com.agileengine.moneyaccountingsystem.entity.AbstractEntity;
import com.agileengine.moneyaccountingsystem.entity.Account;
import com.agileengine.moneyaccountingsystem.exception.MoneyAccountException;
import com.agileengine.moneyaccountingsystem.response.StatusEnum;

public class Utils {


	public static void validateTx(TransactionDto newTx) throws MoneyAccountException {
		if(new Double(newTx.getAmount())<0)
			throw new MoneyAccountException(StatusEnum.ERROR_NEGATIVE_AMOUNT);
	}

	public static void validateNotNull(AbstractEntity entity) throws MoneyAccountException {
		if(entity == null)
			throw new MoneyAccountException(StatusEnum.ERROR_TRANSACTION_NOT_FOUND);
	}

	public static void validateOperation(TransactionDto newTx, Account account) throws MoneyAccountException {
		if (newTx.getType().equals(TypeEnum.CREDIT) && account.getBalance()<newTx.getAmount()) {
			throw new MoneyAccountException(StatusEnum.INSUFFICIENT_FUNDS);
		}
		
	}

}
