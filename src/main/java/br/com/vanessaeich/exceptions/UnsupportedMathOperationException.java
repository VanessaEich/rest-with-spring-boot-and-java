package br.com.vanessaeich.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Vanessa Eich on 10/01/2023
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UnsupportedMathOperationException(String ex) {
        super(ex);
    }
}
