package com.groom.cookiehouse.service.auth;

import com.groom.cookiehouse.controller.dto.response.auth.TokenResponseDto;
import com.groom.cookiehouse.oauth2.userInfo.OAuth2UserInfo;
import com.groom.cookiehouse.config.jwt.JwtService;
import com.groom.cookiehouse.controller.dto.response.auth.SignInResponseDto;
import com.groom.cookiehouse.domain.user.SocialType;
import com.groom.cookiehouse.domain.user.User;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.model.NotFoundException;
import com.groom.cookiehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final Long TOKEN_EXPIRATION_TIME_ACCESS = 100 * 24 * 60 * 60 * 1000L; // 100일
    private final Long TOKEN_EXPIRATION_TIME_REFRESH = 200 * 24 * 60 * 60 * 1000L; // 200일

    private final UserRepository userRepository;

    @Transactional
    public SignInResponseDto signIn(OAuth2UserInfo oAuth2UserInfo, String provider) {
        SocialType socialType = SocialType.valueOf(provider.toUpperCase());
        String socialId = oAuth2UserInfo.getId();
        String userName = oAuth2UserInfo.getName();
        Boolean isRegistered = userRepository.existsBySocialIdAndSocialType(socialId, socialType);

        if (!isRegistered) {
            User newUser = User.builder()
                    .userName(userName)
                    .socialId(socialId)
                    .socialType(socialType)
                    .build();
            User user = userRepository.save(newUser);
            System.out.println(user.getUserName());
        }

        User user = userRepository.findBySocialIdAndSocialType(socialId, socialType)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        String accessToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_ACCESS);
        String refreshToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_REFRESH);

        user.updateRefreshToken(refreshToken);

        return SignInResponseDto.of(user.getId(), user.getUserName(), accessToken, refreshToken, isRegistered);
    }

    @Transactional
    public TokenResponseDto issueToken(String refreshToken) {
        jwtService.verifyToken(refreshToken);

        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        String newAccessToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_ACCESS);
        String newRefreshToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_REFRESH);

        user.updateRefreshToken(newRefreshToken);
        return TokenResponseDto.of(newAccessToken, newRefreshToken);
    }

    @Transactional
    public void signOut(Long userId) {
        System.out.println("여기입니다~~~");
        System.out.println(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        user.updateRefreshToken(null);
    }

}
