package com.iagomassucato.crud.template.exception;

import lombok.Getter;

@Getter
public class DomainValidationException extends RuntimeException {

    private final String field;

    public DomainValidationException(String field, String message) {
        super(message);
        this.field = field;
    }
}
