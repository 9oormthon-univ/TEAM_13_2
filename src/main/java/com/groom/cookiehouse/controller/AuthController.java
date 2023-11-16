package com.groom.cookiehouse.controller;

import com.groom.cookiehouse.common.dto.BaseResponse;
import com.groom.cookiehouse.config.resolver.UserId;
import com.groom.cookiehouse.controller.dto.response.auth.SignInResponseDto;
import com.groom.cookiehouse.controller.dto.response.auth.TokenResponseDto;
import com.groom.cookiehouse.exception.SuccessCode;
import com.groom.cookiehouse.oauth2.userInfo.OAuth2UserInfo;
import com.groom.cookiehouse.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final HttpSession httpSession;

    @GetMapping("/{provider}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<SignInResponseDto> login(@PathVariable String provider, @RequestParam String code, @RequestParam String state) {
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS, authService.login(code, provider, state));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<SignInResponseDto> signIn() {
        OAuth2UserInfo userInfo = (OAuth2UserInfo) httpSession.getAttribute("oAuth2UserInfo");
        String provider = (String) httpSession.getAttribute("provider");
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS, authService.signIn(userInfo, provider));
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<TokenResponseDto> reissueToken(@RequestHeader String refreshToken) {
        return BaseResponse.success(SuccessCode.RE_ISSUE_TOKEN_SUCCESS, authService.issueToken(refreshToken));
    }

    @PostMapping("/sign-out")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse signOut(@UserId Long userId) {
        authService.signOut(userId);
        return BaseResponse.success(SuccessCode.SIGNOUT_SUCCESS);
    }

}