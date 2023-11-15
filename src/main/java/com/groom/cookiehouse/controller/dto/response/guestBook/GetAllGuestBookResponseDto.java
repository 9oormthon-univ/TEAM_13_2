package com.groom.cookiehouse.controller.dto.response.guestBook;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllGuestBookResponseDto {

    private String houseName;
    private List<GuestBookResponseDto> guestBookResponseDtos;

    public static GetAllGuestBookResponseDto of(String houseName, List<GuestBookResponseDto> guestBookResponseDtos) {
        return new GetAllGuestBookResponseDto(houseName, guestBookResponseDtos);
    }

}
