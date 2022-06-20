package com.bispo.meetup.api.execptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bispo.meetup.api.execptionhandler.Problema.Campo;
import com.bispo.meetup.domain.exception.EntidadeNaoEncontradaException;
import com.bispo.meetup.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntityNotFound(NegocioException ex, WebRequest req) {
		HttpStatus stat = HttpStatus.NOT_FOUND;
		return handleExceptionInternal(ex, getProblema(ex,stat), new HttpHeaders(), stat, req);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleBusiness(NegocioException ex, WebRequest req) {
		HttpStatus stat = HttpStatus.BAD_REQUEST;
		return handleExceptionInternal(ex, getProblema(ex,stat), new HttpHeaders(), stat, req);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Campo> campos = new ArrayList<Problema.Campo>();
		
		for ( ObjectError err : ex.getBindingResult().getAllErrors() ) {

			String campo = ((FieldError) err).getField();
			String msg = this.messageSource.getMessage(err, LocaleContextHolder.getLocale());
			
			campos.add(new Problema.Campo(campo, msg));
			
		}
		
		Problema problema = new Problema();
		
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos estão inválidos. "
				+ "Faça o preenchimento correto e tente novamente.");
		problema.setDataHora(OffsetDateTime.now());
		problema.setCampos(campos);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
		
	}

	
	private Problema getProblema(NegocioException ex, HttpStatus stat) {
		Problema problema = new Problema();

		problema.setStatus(stat.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(OffsetDateTime.now());

		return problema;
		
		
	}

	
}
