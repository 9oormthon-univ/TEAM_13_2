package com.groom.cookiehouse.controller;

import com.groom.cookiehouse.oauth2.*;
import com.groom.cookiehouse.oauth2.service.OAuth2Service;
import com.groom.cookiehouse.oauth2.userInfo.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

//@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final InMemoryOAuth2RequestRepository inMemoryOAuth2RequestRepository;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final RestTemplate restTemplate;
    private final HttpSession httpSession;

    // client에서 로그인 버튼 클릭시 여기로
    @GetMapping("/oauth2/authorize/{provider}")
    public void redirectSocialAuthorizationPage(@PathVariable String provider, @RequestParam(name = "redirect_uri") String redirect_uri, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String state = generateState(); // csrf 공격 방지를 위한 state 값

        // 요청의 정보를 메모리에 저장
        inMemoryOAuth2RequestRepository.saveOAuth2Request(
                state, OAuth2AuthorizationRequest.builder()
                        .referer(request.getHeader("referer")) // 요청이 시작된 웹페이지의 URL 정보
                        .redirectUri(redirect_uri)
                        .build()
        );

        // 구글 또는 카카오에 따라 해당 설정 정보 호출
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(provider);
        OAuth2Service oAuth2Service = new OAuth2Service(restTemplate);
        // 해당 정보를 담아서 로그인 페이지 및 동의하기 페이지로 이동
        oAuth2Service.redirectAuthorizePage(clientRegistration, state, response);
    }

    @RequestMapping("/login/oauth2/code/{provider}")
    public void oAuth2AuthenticationCallback(@PathVariable String provider, OAuth2AuthorizationResponse oAuth2AuthorizationResponse, HttpServletRequest request, HttpServletResponse response) throws Exception {
         httpSession.setAttribute("provider", provider);
        // 인증 요청할 때 저장했던 request 정보
        OAuth2AuthorizationRequest oAuth2AuthorizationRequest = inMemoryOAuth2RequestRepository.deleteOAuth2Request(oAuth2AuthorizationResponse.getState());

        if (oAuth2AuthorizationResponse.getError() != null) {
            redirectWithErrorMessage(
                    oAuth2AuthorizationRequest.getReferer(),
                    oAuth2AuthorizationResponse.getError(),
                    response
            );
            return;
        }

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(provider);
        OAuth2Service oAuth2Service = new OAuth2Service(restTemplate);

        // 토큰과 유저 정보를 요청
        OAuth2Token oAuth2Token = oAuth2Service.getAccessToken(clientRegistration, oAuth2AuthorizationResponse.getCode(), oAuth2AuthorizationResponse.getState());
        OAuth2UserInfo oAuth2UserInfo = oAuth2Service.getUserInfo(clientRegistration, oAuth2Token.getToken());
        httpSession.setAttribute("oAuth2UserInfo", oAuth2UserInfo);
//        response.sendRedirect("http://localhost:8080/auth");
    }

    private void redirectWithErrorMessage(String uri, String message, HttpServletResponse response) throws IOException {
        String redirectUri = UriComponentsBuilder.fromUriString(uri)
                .replaceQueryParam("error", message).encode().build().toUriString();
        response.sendRedirect(redirectUri);
    }

    // csrf 공격 방지를 위한 랜덤 문자열 state 값
    private String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}

