package com.credibanco.assessment.card.service.impl;

import java.security.ProviderException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credibanco.assessment.card.dto.request.AddCardRequest;
import com.credibanco.assessment.card.dto.request.OperationsCardRequest;
import com.credibanco.assessment.card.dto.response.CardDTO;
import com.credibanco.assessment.card.dto.response.CardOperationsDTO;
import com.credibanco.assessment.card.exceptions.ProviderExceptionAdd;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.repository.CardRepository;
import com.credibanco.assessment.card.service.CardService;
import com.credibanco.assessment.card.utils.StatusCodeEnum;

@Service
public class CardServiceImpl implements CardService{

	private final CardRepository cardRepository;
	
	@Autowired
	public CardServiceImpl(CardRepository cardRepository) {
		this.cardRepository=cardRepository;
	}
	
	@Override
	public List<CardDTO> getAll(){
		List<Card> listCard = cardRepository.findAll();
		return listCard.stream().map(this::buildCardDTO).collect(Collectors.toList());
	}
	
	@Override
	public CardDTO getCard(String pan) {
		// TODO Auto-generated method stub
		Optional<Card> optionalC=cardRepository.findByPan(pan);
		return buildCardDTO(optionalC.get());
	}
	
	@Override
	public CardOperationsDTO addCard(AddCardRequest addRequest) {
		Optional<Card> optionalC=cardRepository.findByPan(addRequest.getPan());
		if(!optionalC.isPresent()) {
			Card newCard = new Card();
			newCard.setPan(addRequest.getPan());
			newCard.setEstado("Creada");
			newCard.setCedula(addRequest.getCedula());
			newCard.setTelefono(addRequest.getTelefono());
			newCard.setTitular(addRequest.getTitular());
			newCard.setTipo(addRequest.getTipo());
			newCard.setValidationNum(randomNum());
			
			cardRepository.saveAndFlush(newCard);
			
			return buildResponseAdd(newCard);
		} else {
			throw new ProviderExceptionAdd("La tarjeta ya existe");
		}
	}
	
	@Override
	public CardOperationsDTO updateCard(OperationsCardRequest updateRequest) {
		Optional<Card> optionalC=cardRepository.findByPan(updateRequest.getPan());
		if (optionalC.isPresent()){
			if(updateRequest.getValidation_num()==optionalC.get().getValidationNum()) {
				optionalC.get().setEstado("Enrolada");
				cardRepository.saveAndFlush(optionalC.get());
				return buildResponseUpdate(optionalC.get());
				
			} else {
				throw new ProviderException("El numero de validacion no coincide");
			}
		} else {
			throw new ProviderException("La tarjeta no existe");
		}
	}

	@Override
	@Transactional
	public CardOperationsDTO deleteCard(OperationsCardRequest deleteRequest) {
		Optional<Card> optionalC=cardRepository.findByPan(deleteRequest.getPan());
		if(optionalC.isPresent()) {
			cardRepository.deleteByPan(deleteRequest.getPan());
			cardRepository.flush();
			return buildResponseDelete(optionalC.get());
		}else {
			throw new ProviderException("No se ha eliminado la tarjeta");
		}
	}
	
	private CardDTO buildCardDTO(Card card) {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setPan(hidePan(card.getPan()));
		cardDTO.setTitular(card.getTitular());
		cardDTO.setCedula(card.getCedula());
		cardDTO.setTelefono(card.getTelefono());
		cardDTO.setEstado(card.getEstado());
		return cardDTO;
	}

	public String hidePan(String pan) {
		String hiddenPan = pan.replaceAll("(?<=\\d{6})\\d(?=\\d{4})", "*");
		return hiddenPan;
	}
	
	public CardOperationsDTO buildResponseAdd(Card card)
	{
		CardOperationsDTO cardOp = new CardOperationsDTO();
		cardOp.setResponseCode(StatusCodeEnum.C00.getCode());
		cardOp.setMessage(StatusCodeEnum.C00.getDescription());
		cardOp.setValidationNum(String.valueOf(card.getValidationNum()));
		cardOp.setPan(hidePan(card.getPan()));
		return cardOp;
	}
	
	public CardOperationsDTO buildResponseUpdate(Card card)
	{
		CardOperationsDTO cardOp = new CardOperationsDTO();
		cardOp.setResponseCode("00");
		cardOp.setMessage("Exito");
		cardOp.setPan(hidePan(card.getPan()));
		return cardOp;
	}
	
	public CardOperationsDTO buildResponseDelete(Card card)
	{
		CardOperationsDTO cardOp = new CardOperationsDTO();
		cardOp.setResponseCode("00");
		cardOp.setMessage("Tarjeta eliminada correctamente");
		return cardOp;
	}
	
	public int randomNum() {
		int num= (int) (Math.random() * 100) + 1;
		return num;
	}

}
