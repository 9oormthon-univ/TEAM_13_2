package com.groom.cookiehouse.controller.dto.response.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {

    private Long userId;
    private String userName;
    private Boolean isHouseBuilt;
    private Boolean todayMissionComplete;

    public static UserResponseDto of(
            Long userId,
            String userName,
            Boolean isHouseBuilt,
            Boolean todayMissionComplete
    ) {
        return new UserResponseDto(userId, userName, isHouseBuilt, todayMissionComplete);
    }

}
