package com.groom.cookiehouse.dto;

import com.groom.cookiehouse.domain.GuestBook;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GuestBookResponseDto {
    private long userId;
    private String author;
    private long ornamentId;
    private String content;

    public static GuestBookResponseDto of(GuestBook guestBook){
        return GuestBookResponseDto.builder()
                .userId(guestBook.getId())
                .author(guestBook.getAuthor())
                .ornamentId(guestBook.getOrnament().getId())
                .content(guestBook.getContent())
                .build();
    }

}
