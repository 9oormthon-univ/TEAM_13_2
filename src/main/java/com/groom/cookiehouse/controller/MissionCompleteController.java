package com.groom.cookiehouse.controller;

import com.groom.cookiehouse.common.dto.BaseResponse;
import com.groom.cookiehouse.config.resolver.UserId;
import com.groom.cookiehouse.controller.dto.request.mission.MissionCompleteRequestDto;
import com.groom.cookiehouse.controller.dto.response.mission.CreateMissionCompleteResponseDto;
import com.groom.cookiehouse.controller.dto.response.mission.ReadMissionCompleteResponseDto;
import com.groom.cookiehouse.domain.mission.MissionComplete;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.SuccessCode;
import com.groom.cookiehouse.external.client.aws.S3Service;
import com.groom.cookiehouse.service.mission.MissionCompleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@CrossOrigin("http://127.0.0.1:5173")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/mission-complete")
public class MissionCompleteController {
    private final S3Service s3Service;
    private final MissionCompleteService missionCompleteService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CreateMissionCompleteResponseDto> createMissionComplete(
            @UserId Long userId,
            @Valid @ModelAttribute final MissionCompleteRequestDto requestDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BaseResponse.error(ErrorCode.REQUEST_VALIDATION_EXCEPTION, ErrorCode.REQUEST_VALIDATION_EXCEPTION.getMessage());
        }

        String imageUrl = s3Service.uploadImage(requestDto.getMissionCompleteImage(), "mission_complete");
        final CreateMissionCompleteResponseDto data = missionCompleteService.createMissionComplete(requestDto, userId, imageUrl);
        return BaseResponse.success(SuccessCode.MISSION_COMPLETE_CREATED_SUCCESS, data);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CreateMissionCompleteResponseDto> updateMissionComplete(
            @UserId Long userId,
            @Valid @ModelAttribute final MissionCompleteRequestDto requestDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BaseResponse.error(ErrorCode.REQUEST_VALIDATION_EXCEPTION, ErrorCode.REQUEST_VALIDATION_EXCEPTION.getMessage());
        }
        MissionComplete missionComplete = missionCompleteService.findMissionComplete(userId, LocalDate.now());
        s3Service.deleteFile(missionComplete.getImage());
        String imageUrl = s3Service.uploadImage(requestDto.getMissionCompleteImage(), "mission_complete");
        final CreateMissionCompleteResponseDto data = missionCompleteService.updateMissionComplete(requestDto, userId, imageUrl);
        return BaseResponse.success(SuccessCode.MISSION_COMPLETE_UPDATED_SUCCESS, data);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ReadMissionCompleteResponseDto> getMissionComplete(@UserId Long userId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        MissionComplete missionComplete = missionCompleteService.findMissionComplete(userId, localDate);
        final ReadMissionCompleteResponseDto data = ReadMissionCompleteResponseDto.of(
                missionComplete.getId(),
                missionComplete.getImage(),
                missionComplete.getContent(),
                missionComplete.getMission().getDate(),
                missionComplete.getFurnitureId()
        );
        return BaseResponse.success(SuccessCode.GET_MISSION_COMPLETE_SUCCESS, data);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<ReadMissionCompleteResponseDto>> getAllMissionComplete(@PathVariable Long userId) {
        final List<ReadMissionCompleteResponseDto> data = missionCompleteService.findAllMissionComplete(userId);
        return BaseResponse.success(SuccessCode.GET_MISSION_COMPLETE_SUCCESS, data);
    }

}
