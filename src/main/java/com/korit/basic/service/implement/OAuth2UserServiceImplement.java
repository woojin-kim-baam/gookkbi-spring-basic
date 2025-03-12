package com.korit.basic.service.implement;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.basic.entity.CustomOAuth2User;

@Service
// OAuth2를 통해서 클라이언트의 정보를 받은 후에 진행할 비즈니스 로직을 작성하는 서비스
// - 반드시 DefaultOAuth2Service 클래스를 확장해야 함
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService{

    // OAuth2 인증 정보를 받고 실행할 비즈니스 로직 메서드
    @Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 인증 서버에서부터 받은 요청에 포함되어 있는 사용자 정보를 받아오는 행위
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 인증서버에 대한 이름
        String registration = userRequest.getClientRegistration().getClientName();

        try{
            System.out.println("Registration: " + registration);
            System.out.println("=========================");
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
            System.out.println("=========================");
            System.out.println("User Name : " + oAuth2User.getName());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new CustomOAuth2User(oAuth2User.getName(), oAuth2User.getAttributes());
    }
}
