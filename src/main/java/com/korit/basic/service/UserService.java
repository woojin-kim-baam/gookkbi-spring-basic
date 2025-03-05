package com.korit.basic.service;

import org.springframework.http.ResponseEntity;

import com.korit.basic.dto.GetUserResponseDto;
import com.korit.basic.dto.PostUserRequestDto;
import com.korit.basic.dto.ResponseDto;

public interface UserService {
    
    ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto);
    // 그런 다음 UserServiceImplement


    // * controller에서 옴
    ResponseEntity<? super GetUserResponseDto> getUser(String userId);
    // * userServiceImplement로
    
    // * controller에서 옴
    ResponseEntity<ResponseDto> deleteUser(String userId);
    // * userServiceImplement로

}
