package br.com.fiap.parkingmeterbr.controller.config;

import br.com.fiap.parkingmeterbr.dto.ErrorApi;
import br.com.fiap.parkingmeterbr.dto.ErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerConfig {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleBadRequest(MethodArgumentNotValidException e) {
        ErrorApi errorApi = new ErrorApi("Request com argumento(s) inválido(s).");
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorApi.setError(new ErrorValidation(error.getField(),
                    messageSource.getMessage(error, LocaleContextHolder.getLocale())));
        });
        return ResponseEntity.badRequest().body(errorApi);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorApi> handleErrorPK(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorApi("Erro de duplicidade!"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleErrorPK(Exception e) {
        return ResponseEntity.internalServerError().build();
    }

}
