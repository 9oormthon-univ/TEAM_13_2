package com.groom.cookiehouse.dto;

import com.groom.cookiehouse.domain.GuestBook;
import com.groom.cookiehouse.domain.Ornament;
import com.groom.cookiehouse.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class GuestBookRequestDto {
    private long userId;
    private String author;
    private long ornamentId;
    private String content;


    public GuestBook toEntity(User user, Ornament ornament){
        return GuestBook.builder()
                .author(this.author)
                .content(this.content)
                .build();
    }
}
