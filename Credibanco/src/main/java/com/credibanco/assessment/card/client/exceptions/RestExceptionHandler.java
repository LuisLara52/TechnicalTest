package com.credibanco.assessment.card.client.exceptions;

import java.security.ProviderException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.credibanco.assessment.card.dto.response.CardOperationsDTO;
import com.credibanco.assessment.card.dto.response.TransactionOperationsDTO;
import com.credibanco.assessment.card.exceptions.ProviderExceptionAdd;
import com.credibanco.assessment.card.exceptions.ProviderExceptionAddTransact;
import com.credibanco.assessment.card.exceptions.ProviderExceptionCancelTransact;
import com.credibanco.assessment.card.exceptions.ProviderExceptionDeleteCard;
import com.credibanco.assessment.card.exceptions.ProviderExceptionGetCard;
import com.credibanco.assessment.card.exceptions.ProviderExceptionUpdCard;
import com.credibanco.assessment.card.utils.StatusCodeEnum;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "", error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        String error = "Validation Failed: ";
        for (ObjectError fieldError : ex.getBindingResult().getAllErrors())
        {
            error += fieldError.getDefaultMessage() + " - ";
        }
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "", error));
    }

    @ExceptionHandler(ProviderException.class)
    protected ResponseEntity<Object> handleProviderError(ProviderException ex)
    {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"400", ex.getMessage(), ex));
    }
    
    @ExceptionHandler(ProviderExceptionGetCard.class)
    protected ResponseEntity<Object> handleProviderErrorGet(ProviderExceptionGetCard ex)
    {
    	String [] exSplit = ex.getMessage().split("-");
    	String pan=exSplit[1];
        return buildResponseEntity(new CardOperationsDTO(StatusCodeEnum.CC01.getCode(),StatusCodeEnum.CC01.getDescription(), pan));
    }
    
    @ExceptionHandler(ProviderExceptionAdd.class)
    protected ResponseEntity<Object> handleProviderErrorAdd(ProviderExceptionAdd ex)
    {
    	String [] exSplit = ex.getMessage().split("-");
    	String pan=exSplit[1];
        return buildResponseEntity(new CardOperationsDTO(StatusCodeEnum.C01.getCode(),StatusCodeEnum.C01.getDescription(), "No generado", pan));
    }
    
    @ExceptionHandler(ProviderExceptionUpdCard.class)
    protected ResponseEntity<Object> handleProviderErrorUpd(ProviderExceptionUpdCard ex)
    {
    	if(ex.getMessage().contains("Tarjeta no existe")) {
    		String [] exSplit = ex.getMessage().split("-");
        	String pan=exSplit[1];
            return buildResponseEntity(new CardOperationsDTO(StatusCodeEnum.CU01.getCode(),StatusCodeEnum.CU01.getDescription(), pan));
    	}else {
    		String [] exSplit = ex.getMessage().split("-");
        	String pan=exSplit[1];
            return buildResponseEntity(new CardOperationsDTO(StatusCodeEnum.CU02.getCode(),StatusCodeEnum.CU02.getDescription(), pan));
    	}
    	
    }
    
    @ExceptionHandler(ProviderExceptionDeleteCard.class)
    protected ResponseEntity<Object> handleProviderErrorDelete(ProviderExceptionDeleteCard ex)
    {
        return buildResponseEntity(new CardOperationsDTO(StatusCodeEnum.CD01.getCode(),StatusCodeEnum.CD01.getDescription()));
    }
    
    @ExceptionHandler(ProviderExceptionAddTransact.class)
    protected ResponseEntity<Object> handleProviderErrorAddT(ProviderExceptionAddTransact ex)
    {
    	if(ex.getMessage().contains("Tarjeta no existe")) {
    		String [] exSplit = ex.getMessage().split("-");
        	String reference=exSplit[1];
            return buildResponseEntity(new TransactionOperationsDTO(StatusCodeEnum.T01.getCode(),StatusCodeEnum.T01.getDescription(),"Rechazada", reference));
    	}else {
    		String [] exSplit = ex.getMessage().split("-");
        	String reference=exSplit[1];
        	return buildResponseEntity(new TransactionOperationsDTO(StatusCodeEnum.T02.getCode(),StatusCodeEnum.T02.getDescription(),"Rechazada", reference));
    	}
    	
    }
    
    @ExceptionHandler(ProviderExceptionCancelTransact.class)
    protected ResponseEntity<Object> handleProviderErrorCancelT(ProviderExceptionCancelTransact ex)
    {
    	if(ex.getMessage().contains("El numero de referencia es invalido")) {
    		String [] exSplit = ex.getMessage().split("-");
        	String reference=exSplit[1];
            return buildResponseEntity(new TransactionOperationsDTO(StatusCodeEnum.TC01.getCode(),StatusCodeEnum.TC01.getDescription(),"Rechazada", reference));
    	}else {
    		String [] exSplit = ex.getMessage().split("-");
        	String reference=exSplit[1];
        	return buildResponseEntity(new TransactionOperationsDTO(StatusCodeEnum.TC02.getCode(),StatusCodeEnum.TC02.getDescription(),"Rechazada", reference));
    	}
    	
    }
    

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInternalError(Exception ex)
    {
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage(), ex));

    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError)
    {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    
    private ResponseEntity<Object> buildResponseEntity(CardOperationsDTO cardOp)
    {
    	if(cardOp.getMessage().contains("No se ha encontrado la tarjeta")) {
    		return new ResponseEntity<>(cardOp, HttpStatus.NOT_FOUND);
    	}else {
    		return new ResponseEntity<>(cardOp, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    private ResponseEntity<Object> buildResponseEntity(TransactionOperationsDTO trOp)
    {
        return new ResponseEntity<>(trOp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}