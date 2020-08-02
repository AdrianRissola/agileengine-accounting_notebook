package com.agileengine.moneyaccountingsystem.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.agileengine.moneyaccountingsystem.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Transaction save(Transaction tx);
	
	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public List<Transaction> findAll();
	

}
