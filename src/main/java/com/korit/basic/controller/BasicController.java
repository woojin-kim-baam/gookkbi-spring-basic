package com.korit.basic.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// @SpringBootApplication
// 해당 어노테이션이 달린 클래스가 있는 패키지를 베이스 패키지로 간주

// @Component
// 스프링에게 해당 클래스를 Spring Bean으로 등록 시켜라고 알려주는 어노테이션

// HTTP METHOD는 무언가를 제약하기 보다는 설명하는 용도이다


// Controller 레이어 :
// - 클라이언트와 서버 간의 접점에 해당
// - 클라이언트의 요청을 받고 해당 요청에 대한 응답을 처리
// - 각 요청의 HTTP method와 url에 해당하는 메서드를 작성하는 공간 

// RestController : JSON 형태의 ResponseBody를 반환하는 Controller임을 명시하는 어노테이션
// @Contoller + @ResponseBody
// @Controller : 해당 클래스가 HTML 형태의 ResponseBody를 반환하는 Controller임을 명시하는 어노테이션
// @ResponseBody : 컨트롤러의 응답 형태를 직접 조작할 수 있도록 하는 어노테이션
@RestController

// @RequestMapping : HTTP 요청에 클래스 혹은 메서드를 URL 및 HTTP Method 등으로 매핑하기 위한 어노테이션
// HTTP GET 127.0.0.1:8080/basic/**
// @RequestMapping(value="/basic", method = RequestMethod.GET)
@RequestMapping("/basic")
public class BasicController {
    
    // GET http://127.0.0.1:8080/basic/first/**
    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public String firstMethod () {
        return "Spring Boot 첫번째 메서드";
    }

// @GetMapping : RequestMapping을 GET method에 한정시킨 어노테이션
// GET method - 클라이언트가 데이터를 받기 위한 메서드
// Request Body X / Response Body O / HTML form O
@GetMapping("")
public String getMehtod() {
    return "GET Method";
}

// @PostMapping : RequestMapping을 POST method에 한정시킨 어노테이션
// POST method - 클라이언트가 서버에 데이터를 전송하기 위한 메서드
//             - 클라이언트가 서버에 데이터를 작성(create에 한정)하기 위한 메서드
// Request Body O / Response Body O / HTML form O
@PostMapping("")
public String postMethod() {
    return "POST Method";
}

// @PutMapping : RequestMapping을 PUT method에 한정시킨 어노테이션
// PUT method - 클라이언트가 서버에 데이터를 생성 혹은 수정하기 위한 메서드
//            - 만약 전송한 데이터가 동일한 데이터가 있다면 수정
//            - 만약 전송한 데이터가 존재하지 않는 데이터면 생성
//?           - 동일한 데이터를 여러번 전송하더라도 한번만 적용됨
// Request Body O / Response Body X / HTML form X
@PutMapping("")
public String putMethod() {
    return "PUT Method";
}

// @PATCHMapping : RequestMapping을 PATCH method에 한정시킨 어노테이션
// PATCH method - 클라이언트가 서버의 일부 데이터를 수정하기 위한 메서드
// Request Body O / Response Body O / HTML form X

@PatchMapping("")
public String patchMethod() {
    return "PATCH Method";
}

// @DELETEMapping : RequestMapping을 DELETE method에 한정시킨 어노테이션
// DELETE method - 클라이언트가 서버에 데이터를 삭제하기 위한 메서드드
// Request Body X(may) / Response Body X(may) / HTML form X


@DeleteMapping("")
public String deleteMethod() {
    return "DELETE Method";
}

// ! 주의 HTTP Method + URL 패턴이 중복되면 런타임 에러 발생
// @GetMapping("/duplicate")
// public void duplicate1() {}

// @GetMapping("/duplicate")
// public void duplicate2() {}

}


