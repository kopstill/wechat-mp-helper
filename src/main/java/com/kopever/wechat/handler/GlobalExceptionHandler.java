package com.kopever.wechat.handler;

import com.google.common.collect.Lists;
import com.kopever.wechat.web.view.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * Created by Lullaby on 2018/1/22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    public JsonResponse resolveException(Exception exception) {
        List<String> messages = Lists.newArrayList();

        if (exception instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) exception).getConstraintViolations();
            for (ConstraintViolation item : constraintViolations) {
                String message = "Item: " + item.getPropertyPath().toString() + "; message: " + item.getMessage();
                log.info(message);
                messages.add(message);
            }
        }

        if (exception instanceof BindException) {
            BindException bindException = (BindException) exception;
            List<FieldError> errors = bindException.getFieldErrors();
            for (FieldError error : errors) {
                messages.add(error.getField() + error.getDefaultMessage());
            }
        }

        log.error("GlobalExceptionHandler.resolveException.exception", exception);

        JsonResponse<List> response = new JsonResponse<>();
        response.setCode(-1);
        response.setMessage("exception");
        response.setResult(messages.isEmpty() ? null : messages);

        return response;
    }

}
