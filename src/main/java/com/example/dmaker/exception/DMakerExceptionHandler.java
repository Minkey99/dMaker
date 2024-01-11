package com.example.dmaker.exception;

import com.example.dmaker.dto.DMakerErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.dmaker.exception.DMakerErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.dmaker.exception.DMakerErrorCode.INVALID_REQUEST;

/* 각 Controller 조언을 함. */
/* @ExceptionHandler : DMakerException 발생시 메서드 안으로 진입.
 *  HttpServletRequest : 헤더, 쿠키, URL 정보 등등... 정보 포함.
 *  */
@Slf4j
@RestControllerAdvice
public class DMakerExceptionHandler {
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DMakerException.class)
    public DMakerErrorResponse handleException(
            DMakerException e,
            HttpServletRequest request
    ){
        log.error("errorCode {}, url {}, message: {},",
                e.getDMakerErrorCode(), request.getRequestURL(), e.getDetailMessage());

        return DMakerErrorResponse.builder()
                .errorCode(e.getDMakerErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }
    /* Controller 내부로 진입 하기도 전에 발생했을때 처리 코드.
    * HttpRequestMethodNotSupportedException : Post 아닌 다른 메서드로 요청하면 예외 발생 클래스.
    * MethodArgumentNotValidException : Validation 문제가 발생하면 예외 발생 클래스.
    *  */
    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    /* 일반적인 Exception */
    public DMakerErrorResponse handleBadRequest(
            Exception e,
            HttpServletRequest request
    ) {
        log.error("url {}, message: {},",
                request.getRequestURL(),
                e.getMessage());

        return DMakerErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }
    /*
    * 나머지 Exception , 최후의 보루
    * 불필요한 Error 처리는 하지말것.
    *  */
    @ExceptionHandler(Exception.class)
    public DMakerErrorResponse handleException(
            Exception e,
            HttpServletRequest request
    ) {
        log.error("url {}, message: {},",
                request.getRequestURL(),
                e.getMessage());

        return DMakerErrorResponse.builder()
                .errorCode(INTERNAL_SERVER_ERROR)
                .errorMessage(INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
