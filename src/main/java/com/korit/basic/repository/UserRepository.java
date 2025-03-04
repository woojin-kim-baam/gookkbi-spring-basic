package com.korit.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.basic.entity.UserEntity;



// DTO로 출발

// User 테이블에 접근할 리포지토리
// JPARepository를 만들면 구현체를 만들필요없음 JPA리포지토리가 만들어줌
@Repository // 제어의 역전을 걸어주기 위해
public interface UserRepository extends JpaRepository<UserEntity, String> {
    // SELECT * FROM user WHERE user_id = ?; 
    
    UserEntity findByUserId(String userId);
    // 바로 위에꺼 적고 다시 UserServiceImplement로
    
    // SELECT * FROM user WHERE user_tel_number = ?;
    UserEntity findByUserTelNumber(String userTelNumber);
    // 바로 위에꺼 적고 다시 UserServiceImplement로

    // 여기서는 ServiceImplement 깨끗하게 만들기 위해 바꾸는거
    boolean existsByUserID(String userId);
    boolean existsByUserTelNumber(String userTelNumber);
}
