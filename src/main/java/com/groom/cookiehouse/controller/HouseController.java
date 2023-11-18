package com.groom.cookiehouse.controller;

import com.groom.cookiehouse.common.dto.BaseResponse;
import com.groom.cookiehouse.config.resolver.UserId;
import com.groom.cookiehouse.controller.dto.request.house.CreateHouseRequestDto;
import com.groom.cookiehouse.controller.dto.response.house.HouseResponseDto;
import com.groom.cookiehouse.exception.SuccessCode;
import com.groom.cookiehouse.service.house.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@CrossOrigin("http://127.0.0.1:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse createHouse(@UserId Long userId, @RequestBody @Valid final CreateHouseRequestDto requestDto) {
        houseService.createHouse(userId, requestDto.getIcingId(), requestDto.getCookieIds(), requestDto.getHouseName());
        return BaseResponse.success(SuccessCode.HOUSE_CREATED_SUCCESS);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<HouseResponseDto> getHouse(@PathVariable Long userId) {
        final HouseResponseDto data = houseService.getHouse(userId);
        return BaseResponse.success(SuccessCode.GET_HOUSE_SUCCESS, data);
    }

}
