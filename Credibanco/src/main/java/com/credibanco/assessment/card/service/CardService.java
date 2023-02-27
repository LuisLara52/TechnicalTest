package com.credibanco.assessment.card.service;

import java.util.List;

import com.credibanco.assessment.card.dto.request.AddCardRequest;
import com.credibanco.assessment.card.dto.request.OperationsCardRequest;
import com.credibanco.assessment.card.dto.response.CardDTO;
import com.credibanco.assessment.card.dto.response.CardOperationsDTO;

public interface CardService {
	List<CardDTO> getAll();
	CardDTO getCard(String pan);
	CardOperationsDTO addCard(AddCardRequest addRequest);
	CardOperationsDTO updateCard(OperationsCardRequest updateRequest);
	CardOperationsDTO deleteCard(OperationsCardRequest deleteRequest);

}
