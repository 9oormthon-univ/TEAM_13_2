package com.groom.cookiehouse.controller;

import com.groom.cookiehouse.common.dto.BaseResponse;
import com.groom.cookiehouse.config.resolver.UserId;
import com.groom.cookiehouse.controller.dto.response.mission.MissionResponseDto;
import com.groom.cookiehouse.exception.SuccessCode;
import com.groom.cookiehouse.service.mission.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("http://127.0.0.1:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/today-mission")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<MissionResponseDto> getTodayMission(@UserId Long id) {
        final MissionResponseDto data = missionService.getTodayMission(id);
        return BaseResponse.success(SuccessCode.GET_MISSION_SUCCESS, data);
    }

}
