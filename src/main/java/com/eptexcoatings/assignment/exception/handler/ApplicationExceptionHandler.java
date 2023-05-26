package com.eptexcoatings.assignment.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@RestControllerAdvice(basePackages = "com.tosan.faceet.warehouse.controller")
@SuppressWarnings("unused")
public class ApplicationExceptionHandler {

    @ExceptionHandler({Throwable.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionInfoDto onInternalServerException(Throwable throwable) {
        return getExceptionInfo(throwable);
    }

    private ExceptionInfoDto getExceptionInfo(Throwable throwable) {
        ExceptionInfoDto exceptionInfoDto = new ExceptionInfoDto();
        exceptionInfoDto.setMessage(throwable.getMessage());
        exceptionInfoDto.setExceptionName(throwable.getClass().getSimpleName());
        return exceptionInfoDto;
    }
}
