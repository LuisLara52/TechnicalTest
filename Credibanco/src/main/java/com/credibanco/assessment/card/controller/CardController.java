package com.credibanco.assessment.card.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.dto.request.AddCardRequest;
import com.credibanco.assessment.card.dto.request.OperationsCardRequest;
import com.credibanco.assessment.card.dto.response.CardDTO;
import com.credibanco.assessment.card.dto.response.CardOperationsDTO;
import com.credibanco.assessment.card.service.CardService;

@RestController(value = "ApiBaseCardController")
@RequestMapping("/card")
public class CardController {

	private final CardService cardService;

	@Autowired
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@GetMapping(value = "/allCards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CardDTO>> getCards() {
		List<CardDTO> inventoryList = cardService.getAll();

		return new ResponseEntity<>(inventoryList, HttpStatus.OK);
	}

	@GetMapping(value = "/getCard", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CardDTO> getCard(@RequestParam("pan") String pan) {
		CardDTO card = cardService.getCard(pan);

		return new ResponseEntity<>(card, HttpStatus.OK);
	}

	@PostMapping(value="/addCard", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CardOperationsDTO> addCard(@Valid @RequestBody AddCardRequest addRequest){
		CardOperationsDTO cardOp = cardService.addCard(addRequest);
		
		return new ResponseEntity<>(cardOp, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/updateCard", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CardOperationsDTO> updateCard(@Valid @RequestBody OperationsCardRequest updateRequest){
		CardOperationsDTO cardOp = cardService.updateCard(updateRequest);
		
		return new ResponseEntity<>(cardOp, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteCard", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CardOperationsDTO> deleteCard(@Valid @RequestBody OperationsCardRequest deleteRequest){
		CardOperationsDTO cardOp = cardService.deleteCard(deleteRequest);
		
		return new ResponseEntity<>(cardOp, HttpStatus.OK);
	}

}
