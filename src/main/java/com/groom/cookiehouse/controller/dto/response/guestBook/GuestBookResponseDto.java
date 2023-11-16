package com.groom.cookiehouse.controller.dto.response.guestBook;

import com.groom.cookiehouse.domain.GuestBook;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GuestBookResponseDto {
    private Long userId;
    private Long ornamentId;
    private String author;
    private String content;

    public static GuestBookResponseDto of(GuestBook guestBook){
        return GuestBookResponseDto.builder()
                .userId(guestBook.getId())
                .author(guestBook.getAuthor())
                .ornamentId(guestBook.getOrnamentId())
                .content(guestBook.getContent())
                .build();
    }

}
