package com.groom.cookiehouse.controller.dto.response.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInResponseDto {
    private Long userId;
    private String userName;
    private String accessToken;
    private String refreshToken;
    private Boolean isRegistered;
    private Boolean isHouseBuilt;

    public static SignInResponseDto of(Long userId, String userName, String accessToken, String refreshToken, Boolean isRegistered, Boolean isHouseBuilt) {
        return new SignInResponseDto(userId, userName, accessToken, refreshToken, isRegistered, isHouseBuilt);
    }
}