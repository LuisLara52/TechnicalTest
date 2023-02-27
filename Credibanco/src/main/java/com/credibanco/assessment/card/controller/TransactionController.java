package com.credibanco.assessment.card.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.dto.request.AddTransactionRequest;
import com.credibanco.assessment.card.dto.request.CancelTransactionRequest;
import com.credibanco.assessment.card.dto.response.TransactionDTO;
import com.credibanco.assessment.card.dto.response.TransactionOperationsDTO;
import com.credibanco.assessment.card.service.TransactionService;

@RestController(value = "ApiBaseTransactionController")
@RequestMapping("/transaction")
public class TransactionController {
	private final TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService=transactionService;
	}
	
	@GetMapping(value = "/allTransactions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionDTO>> getTransactions() {
		List<TransactionDTO> trList = transactionService.getAllTransactions();

		return new ResponseEntity<>(trList, HttpStatus.OK);
	}
	
	@PostMapping(value="/addTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionOperationsDTO> addTransaction(@Valid @RequestBody AddTransactionRequest addRequest){
		TransactionOperationsDTO trOp = transactionService.addTransaction(addRequest);
		
		return new ResponseEntity<>(trOp, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/cancelTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionOperationsDTO> cancelTransaction(@Valid @RequestBody CancelTransactionRequest cancelRequest){
		TransactionOperationsDTO trOp = transactionService.cancelTransaction(cancelRequest);
		
		return new ResponseEntity<>(trOp, HttpStatus.CREATED);
	}
}
