package com.sparta.hanghaejwt.controller;

import com.sparta.hanghaejwt.dto.LoginRequestDto;
import com.sparta.hanghaejwt.dto.SignupRequestDto;
import com.sparta.hanghaejwt.dto.UserResponseDto;
import com.sparta.hanghaejwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

// 프론트에 전달할 때 API 반환 값은 Json Object(key, value)로 보내는 것이 좋다.
// 프론트도 key, value 가 접근하기 편하기 때문
// 응답코드도 같이 반환 하면 프론트에서 응답 메세지 처리할 때 편하다

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);

        // 이 방식을 이용하면 메세지와 응답 코드 모두 전달 가능
        // return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);

    }


    // 로그인
    @PostMapping("/login")
    public UserResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }
}


// HttpServletResponse : server 가 client 로 반환할 때 response Header 에 만든 Token 넣기 위해 사용

// HttpStatus 는 다양한 상태 코드 전달 가능
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
// 여기서 확인