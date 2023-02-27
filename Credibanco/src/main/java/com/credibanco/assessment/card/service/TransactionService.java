package com.credibanco.assessment.card.service;

import java.util.List;

import com.credibanco.assessment.card.dto.request.AddTransactionRequest;
import com.credibanco.assessment.card.dto.request.CancelTransactionRequest;
import com.credibanco.assessment.card.dto.response.TransactionDTO;
import com.credibanco.assessment.card.dto.response.TransactionOperationsDTO;

public interface TransactionService {

	List<TransactionDTO> getAllTransactions();
	TransactionOperationsDTO addTransaction(AddTransactionRequest addRequest);
	TransactionOperationsDTO cancelTransaction(CancelTransactionRequest cancelRequest);

}
