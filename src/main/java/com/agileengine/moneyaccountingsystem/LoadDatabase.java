package com.agileengine.moneyaccountingsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.agileengine.moneyaccountingsystem.commons.TypeEnum;
import com.agileengine.moneyaccountingsystem.dto.TransactionDto;
import com.agileengine.moneyaccountingsystem.entity.Account;
import com.agileengine.moneyaccountingsystem.repository.AccountRepository;
import com.agileengine.moneyaccountingsystem.service.MoneyAccountService;

@Configuration

class LoadDatabase {

  
	@Bean
	CommandLineRunner initDatabase(MoneyAccountService service, AccountRepository accountRepository) {
		return args -> {
			System.out.println("Preloading " + accountRepository.save(new Account(0D)));
			System.out.println("Preloading " + service.commitTransaction(new TransactionDto(TypeEnum.DEBIT, 10.5D)));
			System.out.println("Preloading " + service.commitTransaction(new TransactionDto(TypeEnum.CREDIT, 1.1D)));
    
		};
	}

}