package com.agileengine.moneyaccountingsystem.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.agileengine.moneyaccountingsystem.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Account save(Account account);
	

}
