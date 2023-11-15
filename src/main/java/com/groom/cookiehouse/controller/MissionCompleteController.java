package com.groom.cookiehouse.controller;

import com.groom.cookiehouse.common.dto.BaseResponse;
import com.groom.cookiehouse.config.resolver.UserId;
import com.groom.cookiehouse.controller.dto.request.mission.MissionCompleteRequestDto;
import com.groom.cookiehouse.controller.dto.response.mission.MissionCompleteResponseDto;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.SuccessCode;
import com.groom.cookiehouse.service.mission.MissionCompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mission-complete")
public class MissionCompleteController {
    private final MissionCompleteService missionCompleteService;

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public BaseResponse<MissionCompleteResponseDto> createMissionComplete(
//            @UserId Long userId,
//            @Valid @ModelAttribute final MissionCompleteRequestDto requestDto,
//            BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return BaseResponse.error(ErrorCode.REQUEST_VALIDATION_EXCEPTION, ErrorCode.REQUEST_VALIDATION_EXCEPTION.getMessage());
//        }
//
//        String imageUrl =
//        final MissionCompleteResponseDto data = missionCompleteService.createMissionComplete(requestDto, userId, imageUrl);
//        return BaseResponse.success(SuccessCode.HOUSE_CREATED_SUCCESS, data);
//    }


}
