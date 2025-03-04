package com.korit.basic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.basic.dto.PostUserRequestDto;
import com.korit.basic.dto.ResponseDto;
import com.korit.basic.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// 그 다음은 userService로

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    // userServiceImplement에서 옴

    private final UserService userService;
    
    @PostMapping()
    public ResponseEntity<ResponseDto> postUser(
        @RequestBody @Valid PostUserRequestDto requestBody 
    ) {
        ResponseEntity<ResponseDto> response = userService.postUser(requestBody);
        return response;
    }

    // ExceptionHandler로 다시 가자구구

}
