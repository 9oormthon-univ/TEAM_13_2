package com.groom.cookiehouse.controller;

import com.groom.cookiehouse.common.dto.BaseResponse;
import com.groom.cookiehouse.controller.dto.response.guestBook.GetAllGuestBookResponseDto;
import com.groom.cookiehouse.controller.dto.request.guestBook.GuestBookRequestDto;
import com.groom.cookiehouse.exception.SuccessCode;
import com.groom.cookiehouse.service.guestBook.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/guest-book")
public class GuestBookController {

    private final GuestBookService guestBookService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse addGuestBook(@RequestBody @Valid GuestBookRequestDto guestBookRequestDto){
        guestBookService.addGuestBook(guestBookRequestDto);
        return BaseResponse.success(SuccessCode.GUESTBOOK_CREATE_SUCCESS);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GetAllGuestBookResponseDto> getAllGuestBookList(@PathVariable Long userId){
        final GetAllGuestBookResponseDto data = guestBookService.getAllGuestBook(userId);
        return BaseResponse.success(SuccessCode.GET_ALL_GUESTBOOK_SUCCESS, data);
    }


}
