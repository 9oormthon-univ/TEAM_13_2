package com.groom.cookiehouse.controller;

import com.groom.cookiehouse.common.dto.BaseResponse;
import com.groom.cookiehouse.domain.GuestBook;
import com.groom.cookiehouse.dto.GuestBookRequestDto;
import com.groom.cookiehouse.dto.GuestBookResponseDto;
import com.groom.cookiehouse.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guestBook")
public class GuestBookController {
    @Autowired
    private final GuestBookService guestBookService;

    @PostMapping("")
    public BaseResponse<GuestBookResponseDto> addGuestBook(@RequestBody GuestBookRequestDto guestBookRequestDto){
        return BaseResponse.ok(guestBookService.addGuestBook(guestBookRequestDto));
    }

    @GetMapping("/all")
    public BaseResponse<List<GuestBook>> getAllGuestBookList(){
        return BaseResponse.ok(guestBookService.getAllGuestBook());
    }


}
