package com.example.dmaker.dto;

import com.example.dmaker.exception.DMakerErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/* Error 발생시 응답하는 클래스 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DMakerErrorResponse {
    private DMakerErrorCode errorCode;
    private String errorMessage;
}
