package com.korit.basic.entity;

import com.korit.basic.dto.PatchUserRequestDto;
import com.korit.basic.dto.PostUserRequestDto;
import com.korit.basic.dto.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 이게 1번
// UserRepository로 ㄱㄱㄱ
// entity 클래스, entity 명은 user
// practice_sql 데이터베이스의 user테이블과 매핑
@Entity(name = "user")
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 기본생성자 쓰면 set메서드를 통해 불변성 유지 x , 모든생성자를 쓰면 불변성은 보장되나 코드의 가독성이 떨어져 헷갈림(직관적이지 못함), 이 해결책이 Builder임.
public class UserEntity {
    // @Column : 이름이 동일해서 굳이 얘는 할 필요 X

    @Id
    private String userId;

    private String userPassword;

    private String userName;

    private String userAddress;

    private String userTelNumber;

    // 선생님 방식임 UserServiceImplement에서 이어져옴, 
    public UserEntity(PostUserRequestDto dto) {
        this.userId = dto.getUserId();
        this.userPassword = dto.getUserPassword();
        this.userName = dto.getUserName();
        this.userAddress = dto.getUserAddress();
        this.userTelNumber = dto.getUserTelNumber();
    }

    // 애는 암호화된 비밀번호에서 옴(SecurityServiceImplement)
    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.userPassword = dto.getUserPassword();
        this.userName = dto.getUserName();
        this.userAddress = dto.getUserAddress();
        this.userTelNumber = dto.getUserTelNumber();
    }

    public void patch(PatchUserRequestDto dto) {
        this.userName = dto.getUserName();
        this.userAddress = dto.getUserAddress();
    }
}
