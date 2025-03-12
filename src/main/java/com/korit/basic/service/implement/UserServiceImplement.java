package com.korit.basic.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.korit.basic.dto.GetUserListResponseDto;
import com.korit.basic.dto.GetUserResponseDto;
import com.korit.basic.dto.PatchUserRequestDto;
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

    // * (2) implement에서 옴 
    public void queryMethod(){
        UserEntity userEntity = new UserEntity();

        // save(엔터티) : 인스턴스를 레코드로 저장하는 메서드
        // 만약 엔터티의 ID에 해당하는 데이터가 동일한 데이터가 테이블에 존재한다면 수정
        // 만약 엔터티의 ID에 해당하는 데이터가 동일한 데이터가 테이블에 존재하지 않는다면 삽입 
        userRepository.save(userEntity);

        // saveAll(엔터티 컬렉션) : 컬렉션으로 관리되어지는 엔터티 인스턴스를 모두 저장
        List<UserEntity> entities = new ArrayList<>();
        userRepository.saveAll(entities);

        // findByID(아이디 데이터) : 아이디를 기준으로 조회
        // - 반환 타입이 Optional 타입으로 반환되기 때문에 불편함
        // - 직접 쿼리 메서드를 작성하는 것이 편함
        userEntity = userRepository.findById("아이디").get(); 
        // optional이라는 타입으로 뜨기에 get메서드를 사용해야 데이터를 가져올 수 있다, 존재하지 않는다면 에러가 터진다, 그래서 직접적으로 쿼리메서드 작성이 훨씬 ㄱㅊ음

        // findAll() : 전체 레코드 조회
        entities = userRepository.findAll();

        // existsById(아이디 데이터) : 아이디 기준으로 레코드 존재여부 반환
        boolean existed = userRepository.existsById("아이디");

        // deleteById(아이디 데이터) : 아이디 기준으로 레코드 삭제
        userRepository.deleteById("아이디");

        // delete(엔터티) : 해당 엔터티 레코드를 삭제
        userRepository.delete(userEntity);
       
        // deleteAll(엔터티 컬렉션) : 해당하는 엔터티 레코드 리스트를 삭제
        userRepository.deleteAll(entities);

        // * 다시 userRepository로
    }

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
        String userTelNumber = dto.getUserTelNumber();

        try {
            boolean isExistUserId = userRepository.existsByUserId(userId);

            if(isExistUserId) return ResponseDto.duplicatedId();
            boolean isExistsUserTelNumber = userRepository.existsByUserTelNumber(userTelNumber);
            if(isExistsUserTelNumber) return ResponseDto.duplicatedTelNumber();
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);


        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }


        // boolean isExistUserId = userRepository.existsByUserId(userId);
        //  if(isExistUserId) {
        //     ResponseDto responseBody = new ResponseDto("DI", "Duplicated Id");
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        // }
        // ! 이건 또정리(2)
        //  if(isExistUserId) return ResponseDto.duplicatedId();

        // ! 이건 깨끗하게
        // boolean isExistsUserTelNumber = userRepository.existsByUserTelNumber(userTelNumber);
        // if(isExistsUserTelNumber) {
        //     ResponseDto responseBody = new ResponseDto("DT", "Duplicated Tel number");
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        // }
        // ! 이건 또정리(2)
            // if(isExistsUserTelNumber) return ResponseDto.duplicatedTelNumber();

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
        // UserEntity userEntity = new UserEntity(dto);
        
        // save시키기
        // userRepository.save(userEntity);

        // ! 정리해서 지금 지운거 (2)
        // ResponseDto response = new ResponseDto("SU", "Success.");
        // return ResponseEntity.status(HttpStatus.CREATED).body(response);

        // ! 이건 또정리(2)
        return ResponseDto.success(HttpStatus.CREATED);

        // 이렇게 다만들면 userController로 다시 ㄱㄱㄱ
    }

    // UserService에서 옴(3)
    @Override
    public ResponseEntity<? super GetUserListResponseDto> getUserList() {

        List<UserEntity> userEntities = new ArrayList<>();

        try {
            // 이거 만들때 UserRepository가서 findByOrderByUserIdAsc만들고 옴
            userEntities =  userRepository.findByOrderByUserIdAsc();

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserListResponseDto.success(userEntities);
    }


    // * UserService에서 옴
    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String userId) {
        
        UserEntity userEntity = null;
        
        try {
            userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistsUser();
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetUserResponseDto.success(userEntity);
        
    }
    

    // * UserService에서 옴
    @Override
    public ResponseEntity<ResponseDto> deleteUser(String userId) {
        
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.noExistsUser();
            
            userRepository.delete(userEntity); 
        }catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return ResponseDto.success(HttpStatus.OK);
        
    }
    
    // UserService에서 옴
    @Override
    public ResponseEntity<ResponseDto> patchUser(String userId, PatchUserRequestDto dto) {
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.noExistsUser();

            userEntity.patch(dto);
            userRepository.save(userEntity);
            
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    
    
  }