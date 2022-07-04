package com.cc.test301.common.controller;

import com.cc.test301.common.utils.AppResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = RuntimeException.class)
	protected ResponseEntity<?> handleInternalServerError(RuntimeException ex, WebRequest request) {
		return handleExceptionInternal(ex,
		                               AppResponse.builder()
		                                          .message(ex.getMessage())
		                                          .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
		                                          .throwable(ex)
		                                          .build(),
		                               new HttpHeaders(),
		                               HttpStatus.INTERNAL_SERVER_ERROR,
		                               request);
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	protected ResponseEntity<?> handleIllegalArgumentException(RuntimeException ex, WebRequest request) {
		return handleExceptionInternal(ex,
		                               AppResponse.builder()
		                                          .message(ex.getMessage())
		                                          .status(HttpStatus.NOT_FOUND.getReasonPhrase())
		                                          .throwable(ex)
		                                          .build(),
		                               new HttpHeaders(),
		                               HttpStatus.NOT_FOUND,
		                               request);
	}
}
