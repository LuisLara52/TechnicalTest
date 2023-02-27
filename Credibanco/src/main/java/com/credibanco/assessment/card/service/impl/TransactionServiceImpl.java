package com.credibanco.assessment.card.service.impl;

import java.security.ProviderException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.dto.request.AddTransactionRequest;
import com.credibanco.assessment.card.dto.request.CancelTransactionRequest;
import com.credibanco.assessment.card.dto.response.TransactionDTO;
import com.credibanco.assessment.card.dto.response.TransactionOperationsDTO;
import com.credibanco.assessment.card.exceptions.ProviderExceptionAddTransact;
import com.credibanco.assessment.card.exceptions.ProviderExceptionCancelTransact;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.Transaction;
import com.credibanco.assessment.card.repository.CardRepository;
import com.credibanco.assessment.card.repository.TransactionRepository;
import com.credibanco.assessment.card.service.TransactionService;
import com.credibanco.assessment.card.utils.StatusCodeEnum;

@Service
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository transactionRepository; 
	private final CardRepository cardRepository;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository, CardRepository cardRepository) {
		this.transactionRepository=transactionRepository;
		this.cardRepository=cardRepository;
	}
	
	@Override
	public List<TransactionDTO> getAllTransactions(){
		List<Transaction> listTransactions = transactionRepository.findAll();
		return listTransactions.stream().map(this::buildTransactionDTO).collect(Collectors.toList());
	}
	
	@Override
	public TransactionOperationsDTO addTransaction(AddTransactionRequest addRequest) {
		Optional<Transaction> optionalT=transactionRepository.findByReferenceNumber(addRequest.getReference_number());
		if(!optionalT.isPresent()) {
			Optional<Card> optionalC=cardRepository.findByPan(addRequest.getPan());
			if(optionalC.isPresent()) {
				if(optionalC.get().getEstado().equals("Enrolada")) {
					Transaction tr = new Transaction();
					tr.setReference_number(addRequest.getReference_number());
					tr.setPan(addRequest.getPan());
					tr.setTotal_compra(addRequest.getTotal_compra());
					tr.setDireccion_compra(addRequest.getDireccion_compra());
					tr.setEstado("Aprobada");
					System.out.println(currentDate().toString());
					tr.setFecha(currentDate());
					
					transactionRepository.saveAndFlush(tr);
					
					return buildResponseAdd(tr);
				}else {
					Transaction tr = new Transaction();
					tr.setReference_number(addRequest.getReference_number());
					tr.setPan(addRequest.getPan());
					tr.setTotal_compra(addRequest.getTotal_compra());
					tr.setDireccion_compra(addRequest.getDireccion_compra());
					tr.setEstado("Rechazada");
					System.out.println(currentDate().toString());
					tr.setFecha(currentDate());
					
					transactionRepository.saveAndFlush(tr);
					
					throw new ProviderExceptionAddTransact("Tarjeta no enrolada-"+addRequest.getReference_number());
				}

			} else {

				throw new ProviderExceptionAddTransact("Tarjeta no existe-"+addRequest.getReference_number());
			}
		} else {
			throw new ProviderException("Numero de referencia ya existe");
		}
	}
	
	@Override
	public TransactionOperationsDTO cancelTransaction(CancelTransactionRequest cancelRequest) {
		Optional<Transaction> optionalT=transactionRepository.findByReferenceNumber(cancelRequest.getReference_number());
		if(optionalT.isPresent()) {
			if(!optionalT.get().getEstado().equals("Rechazada")) {
				if (isCancel(optionalT.get().getFecha())) {
					optionalT.get().setEstado("Anulada");
					
					transactionRepository.saveAndFlush(optionalT.get());
					
					return buildResponseCancel(optionalT.get());
					
				}else {
					throw new ProviderExceptionCancelTransact("No se puede anular transaccion, pasaron mas de 5 minutos-"+cancelRequest.getReference_number());
				}
			}else {
				throw new ProviderExceptionCancelTransact("No se puede anular transaccion, el estado de la transaccion es 'Rechazada'-"+cancelRequest.getReference_number());
			}

		}else {
			throw new ProviderExceptionCancelTransact("El numero de referencia es invalido-"+cancelRequest.getReference_number());
		}
	}
	
	private TransactionDTO buildTransactionDTO(Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setReference_number(transaction.getReference_number());
		transactionDTO.setTotal_compra(transaction.getTotal_compra());
		transactionDTO.setDireccion_compra(transaction.getDireccion_compra());
		transactionDTO.setEstado(transaction.getEstado());
		transactionDTO.setFecha(transaction.getFecha().toString());
		transactionDTO.setPan(CardServiceImpl.hidePan(transaction.getPan()));
		return transactionDTO;
	}
	
	private TransactionOperationsDTO buildResponseAdd(Transaction tr) {
		TransactionOperationsDTO trOp = new TransactionOperationsDTO();
		trOp.setResponseCode(StatusCodeEnum.T00.getCode());
		trOp.setMessage(StatusCodeEnum.T00.getDescription());
		trOp.setEstado(tr.getEstado());
		trOp.setReferenceNumber(tr.getReference_number());
		
		return trOp;
	}
	
	public Date currentDate () {
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		Date fechaActual = Date.from(fechaHoraActual.atZone(ZoneId.systemDefault()).toInstant());
		return fechaActual;
	}
	
	public boolean isCancel(Date time) {
		long mil1=currentDate().getTime();
		long mil2=time.getTime();
		
		long diferenciaMilisegundos = Math.abs(mil1 - mil2);
		long diferenciaMinutos= diferenciaMilisegundos/60000;
		
		if(diferenciaMinutos<=5) {
			return true;
		}else {
			return false;
		}
	}

	private TransactionOperationsDTO buildResponseCancel(Transaction tr) {
		TransactionOperationsDTO trOp = new TransactionOperationsDTO();
		trOp.setResponseCode(StatusCodeEnum.TC00.getCode());
		trOp.setMessage(StatusCodeEnum.TC00.getDescription());
		trOp.setReferenceNumber(tr.getReference_number());
		
		return trOp;
	}

}
