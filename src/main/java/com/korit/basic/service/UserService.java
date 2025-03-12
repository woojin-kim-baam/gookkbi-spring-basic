package com.korit.basic.service;

import org.springframework.http.ResponseEntity;

import com.korit.basic.dto.GetUserListResponseDto;
import com.korit.basic.dto.GetUserResponseDto;
import com.korit.basic.dto.PatchUserRequestDto;
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
    
    
    // controller에서 옴(3)
     ResponseEntity<? super GetUserListResponseDto> getUserList();
    
     // controller에서 옴 (4)
     ResponseEntity<ResponseDto> patchUser(String userId, PatchUserRequestDto dto);
     

}
