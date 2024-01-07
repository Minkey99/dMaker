package com.example.dmaker.exception;

import lombok.Getter;

/* custom exception */
@Getter
public class DMakerException extends RuntimeException{
    private DMakerErrorCode dMakerErrorCode;
    private String detailMessage;

    public DMakerException(DMakerErrorCode errorCode){
        super(errorCode.getMessage());
        this.dMakerErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    /* 2번째 인자에 더 디테일한 메세지 전달 가능. */
    public DMakerException(DMakerErrorCode errorCode, String detailMessage){
        super(errorCode.getMessage());
        this.dMakerErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
