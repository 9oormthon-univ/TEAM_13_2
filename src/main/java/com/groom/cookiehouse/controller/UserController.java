package com.groom.cookiehouse.controller;

import com.groom.cookiehouse.common.dto.BaseResponse;
import com.groom.cookiehouse.config.resolver.UserId;
import com.groom.cookiehouse.controller.dto.response.user.UserResponseDto;
import com.groom.cookiehouse.exception.SuccessCode;
import com.groom.cookiehouse.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("http://127.0.0.1:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UserResponseDto> getUser(@UserId Long userId) {
        final UserResponseDto data = userService.getUser(userId);
        return BaseResponse.success(SuccessCode.GET_USER_INFO_SUCCESS, data);
    }

}
