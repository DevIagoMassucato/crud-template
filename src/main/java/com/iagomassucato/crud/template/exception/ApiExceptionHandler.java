package com.iagomassucato.crud.template.exception;

import com.iagomassucato.exception.core.ErrorEnum;
import com.iagomassucato.exception.core.ExceptionResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final ExceptionResponseFactory exceptionResponseFactory;

    @ExceptionHandler(DomainValidationException.class)
    public ProblemDetail handleValidation(
            DomainValidationException ex,
            HttpServletRequest httpServletRequest) {

        return exceptionResponseFactory.createProblemDetail(
                ErrorEnum.INVALID_FIELD,
                Map.of(ex.getField(), ex.getMessage()),
                httpServletRequest.getRequestURI()
        );
    }
}
