package com.korit.basic.dto;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.basic.entity.UserEntity;

import lombok.Getter;

// *
// * 여기가 READ의 시작
@Getter
public class GetUserResponseDto extends ResponseDto {
    private String userId;
    private String userName;
    private String userAddress;
  
    private GetUserResponseDto(UserEntity userEntity) {
      super("SU", "Success");
      this.userId = userEntity.getUserId();
      this.userName = userEntity.getUserName();
      this.userAddress = userEntity.getUserAddress();
    }
  
    public static ResponseEntity<GetUserResponseDto> success(UserEntity userEntity) {
      GetUserResponseDto responseBody = new GetUserResponseDto(userEntity);
      return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
  
  }
// * userController로
