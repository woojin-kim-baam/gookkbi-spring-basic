package com.korit.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// UserController로 넘어가도록

@Getter
@AllArgsConstructor
public class ResponseDto {
    private String code;
    private String message;
}
