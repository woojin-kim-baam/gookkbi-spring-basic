package com.korit.basic.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.korit.basic.dto.PostUserRequestDto;
import com.korit.basic.dto.ResponseDto;
import com.korit.basic.entity.UserEntity;
import com.korit.basic.repository.UserRepository;
import com.korit.basic.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{

    // 데이터가 있으면 꺼내오기 위해 그걸 확인하려면 repository에서 봐야되니깐 userRepository 의존성 주입(생성자를 이용해 주입할 것, Autowired 안써도 됨)

    private final UserRepository userRepository;
    // 쓰고 다시 userRepository로
    @Override
    public ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto) {

        //? String userId = dto.getUserId();
        // UserEntity userEntity = userRepository.findByUserId(userId);
        // if(userEntity != null) {
        //     ResponseDto responseBody = new ResponseDto("DI", "Duplicated Id");
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        // }
        // 쓰고 다시 userRepository로

        //? String userTelNumber = dto.getUserTelNumber();
        // userEntity = userRepository.findByUserTelNumber(userTelNumber);
        // if(userEntity != null) {
        //     ResponseDto responseBody = new ResponseDto("DT", "Duplicated Tel number");
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        // }

        // 다쓰고 UserEntity 가서 Builder 만들기 그리고 밑에처럼 인스턴스 생성

        //? userEntity = new UserEntity(userId, dto.getUserPassword(), dto.getUserName(), dto.getUserAddress(), userTelNumber );

         // ! 이건 깨끗하게
         String userId = dto.getUserId();
         Boolean isExistUserId = userRepository.existsByUserID(userId);
         if(isExistUserId) {
            ResponseDto responseBody = new ResponseDto("DI", "Duplicated Id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }

        String userTelNumber = dto.getUserTelNumber();
        // ! 이건 깨끗하게
        boolean isExistsUserTelNumber = userRepository.existsByUserTelNumber(userTelNumber);
        if(isExistsUserTelNumber) {
            ResponseDto responseBody = new ResponseDto("DT", "Duplicated Tel number");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
        //? UserEntity userEntity = new UserEntity(userId, dto.getUserPassword(), dto.getUserName(), dto.getUserAddress(), userTelNumber );

        //! 빌더패턴으로 바꾸기
        // 빌더패턴 : 객체 생성 과정에 멤버변수 별로 객체를 구성한 후 객체를 생성할 수 있도록 도움을 주는 생성패턴
        // 특징 : 가독성 향상, 객체에 불변성을 보장
        //! UserEntity userEntity = UserEntity
        //                             .builder()
        //                             .userId(userId)
        //                             .userPassword(dto.getUserPassword())
        //                             .userName(dto.getUserName())
        //                             .userAddress(dto.getUserAddress())
        //                             .userTelNumber(userTelNumber)
        //                             .build();

        // 선생님 방식
        UserEntity userEntity = new UserEntity(dto);
        
        // save시키기
        userRepository.save(userEntity);

        ResponseDto response = new ResponseDto("SU", "Success.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        // 이렇게 다만들면 userController로 다시 ㄱㄱㄱ
    }
    
}
