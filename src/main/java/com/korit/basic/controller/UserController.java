package com.korit.basic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.basic.dto.GetUserResponseDto;
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

    // ExceptionHandler로 다시 가자구

    // * GetUserResponseDto에서 옴
    @GetMapping("/{userId}")
    public ResponseEntity<? super GetUserResponseDto> getUser(
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(userId);
        return response;
    }
    // * Service로 출동

    // 삭제 시작
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<ResponseDto> response = userService.deleteUser(userId);
        return response;
    }
    // * Service로 출동

}
