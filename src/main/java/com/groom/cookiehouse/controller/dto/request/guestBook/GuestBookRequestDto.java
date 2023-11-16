package com.groom.cookiehouse.controller.dto.request.guestBook;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@Setter
public class GuestBookRequestDto {

    @NotNull
    private Long userId;
    @NotBlank
    private String author;
    @NotNull
    private Long ornamentId;
    @NotBlank
    private String content;

}
