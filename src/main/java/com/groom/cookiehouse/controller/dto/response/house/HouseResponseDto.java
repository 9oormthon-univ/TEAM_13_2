package com.groom.cookiehouse.controller.dto.response.house;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HouseResponseDto {

    private Long icingId;
    private List<Long> cookieId;
    private String houseName;

    public static HouseResponseDto of(Long icingId, List<Long> cookieId, String houseName) {
        return new HouseResponseDto(icingId, cookieId, houseName);
    }

}
