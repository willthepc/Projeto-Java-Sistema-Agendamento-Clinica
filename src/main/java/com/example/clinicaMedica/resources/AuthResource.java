package com.example.clinicaMedica.resources;

import com.example.clinicaMedica.config.TokenProvider;
import com.example.clinicaMedica.dto.LoginRequestDto;
import com.example.clinicaMedica.dto.TokenResponseDto;
import com.example.clinicaMedica.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthResource {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        TokenResponseDto login = loginService.login(loginRequestDto);
        return ResponseEntity.ok().body(login);
    }
}
