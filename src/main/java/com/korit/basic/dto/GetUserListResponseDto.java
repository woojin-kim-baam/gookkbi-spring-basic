package com.korit.basic.dto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.basic.entity.UserEntity;
import com.korit.basic.vo.User;

import lombok.Getter;
// * 3
@Getter
public class GetUserListResponseDto extends ResponseDto {

    private List<User> userList;
    // * User.java로

    // User.java에서 옴
    private GetUserListResponseDto(List<UserEntity> userEntities) {
        super("SU", "Success");
        this.userList = User.getList(userEntities);
    }
    
    public static ResponseEntity<GetUserListResponseDto> success(List<UserEntity> userEntities){
        GetUserListResponseDto responsebody = new GetUserListResponseDto(userEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);

    } 
    // 컨트롤러로

    

    
}
