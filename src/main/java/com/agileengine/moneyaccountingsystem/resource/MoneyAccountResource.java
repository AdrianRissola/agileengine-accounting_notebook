package com.agileengine.moneyaccountingsystem.resource;

import java.util.List;

import javax.persistence.LockTimeoutException;
import javax.persistence.PessimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agileengine.moneyaccountingsystem.commons.Utils;
import com.agileengine.moneyaccountingsystem.dto.TransactionDto;
import com.agileengine.moneyaccountingsystem.exception.MoneyAccountException;
import com.agileengine.moneyaccountingsystem.response.GenericResponse;
import com.agileengine.moneyaccountingsystem.response.StatusEnum;
import com.agileengine.moneyaccountingsystem.service.MoneyAccountService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("moneyAccountService")
public class MoneyAccountResource {
	
	@Autowired
	private MoneyAccountService moneyAccountService;
	
	@GetMapping("/getTransactions")
	public ResponseEntity<GenericResponse> getTransactions(){
		ResponseEntity<GenericResponse> response = null;
		GenericResponse<List<TransactionDto>> genericResponse = null;
		try{
			List<TransactionDto>  txs = this.moneyAccountService.getTransactions();
			genericResponse = new GenericResponse<List<TransactionDto>>(txs, StatusEnum.SUCCESSFUL_OPERATION);
			response = new ResponseEntity<>(genericResponse, HttpStatus.OK);
		} catch (PessimisticLockException|LockTimeoutException e) {
			response = new ResponseEntity<>(new GenericResponse<>(null, StatusEnum.OPERATION_TEMPORARILY_UNAVAILABLE), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(new GenericResponse<>(null, StatusEnum.ERROR_INVALID_STATUS_VALUE), HttpStatus.OK);
		}
		return response;
	}
	
	@GetMapping("/getBalance")
	public ResponseEntity<GenericResponse> getBalance(){
		ResponseEntity<GenericResponse> response = null;
		GenericResponse<Double> genericResponse = null;
		try{
			Double balance = this.moneyAccountService.getBalance();
			genericResponse = new GenericResponse<Double>(balance, StatusEnum.SUCCESSFUL_OPERATION);
			response = new ResponseEntity<>(genericResponse, HttpStatus.OK);
		} catch (PessimisticLockException|LockTimeoutException e) {
			response = new ResponseEntity<>(new GenericResponse<>(null, StatusEnum.OPERATION_TEMPORARILY_UNAVAILABLE), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(new GenericResponse<>(null, StatusEnum.ERROR_INVALID_STATUS_VALUE), HttpStatus.OK);
		}
		return response;
	}
	
	@PostMapping("/commitTransaction")
	public ResponseEntity<GenericResponse> commitTransaction(@RequestBody TransactionDto newTx){
		ResponseEntity<GenericResponse> response = null;
		GenericResponse<TransactionDto> genericResponse = null;
		try {
			Utils.validateTx(newTx);
			TransactionDto tx = this.moneyAccountService.commitTransaction(newTx);
			genericResponse = new GenericResponse<TransactionDto>(tx, StatusEnum.SUCCESSFUL_OPERATION);
			response = new ResponseEntity<>(genericResponse, HttpStatus.OK);
		} catch (MoneyAccountException mae) {
			response = new ResponseEntity<>(new GenericResponse<>(null, mae.getStatusEnum()), HttpStatus.OK);
		} catch (PessimisticLockException|LockTimeoutException e) {
			response = new ResponseEntity<>(new GenericResponse<>(null, StatusEnum.OPERATION_TEMPORARILY_UNAVAILABLE), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(new GenericResponse<>(null, StatusEnum.ERROR_INVALID_STATUS_VALUE), HttpStatus.OK);
		}
		return response;
	}
	
/*	@GetMapping("/findTransaction/{id}")
	public ResponseEntity<GenericResponse> findTransaction(@PathVariable String id){
		ResponseEntity<GenericResponse> response = null;
		GenericResponse<TransactionDto> genericResponse = null;
		try {
			TransactionDto tx = this.moneyAccountService.findTransactionById(id);
			genericResponse = new GenericResponse<TransactionDto>(tx, StatusEnum.SUCCESSFUL_OPERATION);
			response = new ResponseEntity<>(genericResponse, HttpStatus.OK);
		} catch (MoneyAccountException mae) {
			response = new ResponseEntity<>(new GenericResponse<>(null, mae.getStatusEnum()), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(new GenericResponse<>(null, StatusEnum.ERROR_INVALID_STATUS_VALUE), HttpStatus.OK);
		}
		return response;
	}*/


	

	

	

}
