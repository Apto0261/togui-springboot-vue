package com.project.togui.backend.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.togui.backend.dto.SignRequest;
import com.project.togui.backend.dto.SignResponse;
import com.project.togui.backend.repository.MemberRepository;
import com.project.togui.backend.security.JwtProvider;
import com.project.togui.backend.service.SignService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignController {

    private final MemberRepository memberRepository;
    private final SignService signService;
    private final JwtProvider jwtProvider;


    @PostMapping("/login")
    public ResponseEntity<SignResponse> login(@RequestBody SignRequest request, HttpServletResponse res) throws Exception {
        SignResponse signresponse = signService.login(request);
        String token = signresponse.getToken();

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        res.addCookie(cookie);

        return new ResponseEntity<>(signresponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }

    @GetMapping("/register/nicknamechk/{nickname}")
    public ResponseEntity<Boolean> isValidNickname(@PathVariable String nickname) throws Exception{
        return ResponseEntity.ok(signService.isValidNickname(nickname));
    }

    @GetMapping("/register/emailchk/{email}")
    public ResponseEntity<Boolean> isValidEmail(@PathVariable String email) throws Exception{
        return ResponseEntity.ok(signService.isValidEmail(email));
    }

    @GetMapping("/user/get")
    public ResponseEntity<SignResponse> getUser(@RequestParam String email) throws Exception {
        System.out.println("유저 겟 ");
        return new ResponseEntity<>( signService.getMember(email), HttpStatus.OK);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<SignResponse> getUserForAdmin(@RequestParam String email) throws Exception {
        return new ResponseEntity<>( signService.getMember(email), HttpStatus.OK);
    }

    @GetMapping(value="/check")
    public ResponseEntity<String> check(@CookieValue(value = "token",required = false) String token) {
        Claims claims = jwtProvider.getClaims(token);

        if(claims != null){
            return new ResponseEntity<>(claims.getSubject(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);

    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse res) throws Exception {
        Cookie cookie = new Cookie("token",null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
