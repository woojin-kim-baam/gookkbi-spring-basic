package com.korit.basic.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

// UserController로 넘어가도록

@Getter
@AllArgsConstructor
public class ResponseDto {
    private String code;
    private String message;

    // 또 정리 (2)
    public static ResponseEntity<ResponseDto> success(HttpStatus status){
        ResponseDto response = new ResponseDto("SU", "Success.");
        return ResponseEntity.status(status).body(response);
    }

    public static ResponseEntity<ResponseDto> databaseError(){
        ResponseDto response = new ResponseDto("DBE", "DatabaseError.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static ResponseEntity<ResponseDto> duplicatedId(){
        ResponseDto responseBody = new ResponseDto("DI", "Duplicated Id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedTelNumber(){
         ResponseDto responseBody = new ResponseDto("DT", "Duplicated Tel number");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    // * GetUSerResposnseDto에서 옴
    public static ResponseEntity<ResponseDto> noExistsUser() {
        ResponseDto responseBody = new ResponseDto("NU", "No Exist User");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}


