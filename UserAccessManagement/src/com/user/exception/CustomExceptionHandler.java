package com.user.exception;

import javax.management.relation.InvalidRoleValueException;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5045003388952260008L;

	@ExceptionHandler({ UsernameNotFoundException.class, ObjectNotFoundException.class })
	public ResponseEntity<String> handleUsernameNotFoundException(Exception exception) {
		return new ResponseEntity<>("The user does not exist with id: " + exception.getLocalizedMessage(),
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	public ResponseEntity<String> handleAuthenticationCredentialsNotFoundException(
			AuthenticationCredentialsNotFoundException exception) {
		return new ResponseEntity<>("Authentication credentials not found in the request", HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception) {
		return new ResponseEntity<>(exception.getCause().getMessage(), HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(InvalidRoleValueException.class)
	public ResponseEntity<String> handleInvalidRoleValueException(InvalidRoleValueException exception) {
		return new ResponseEntity<>("Invalid Value for role", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception exception) {
		return new ResponseEntity<>("Some exception occured while trying to serve the request. Please try again later.",
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
