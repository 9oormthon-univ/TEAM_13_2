package com.groom.cookiehouse.controller.dto.request.mission;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MissionCompleteRequestDto {

    @NotNull
    private MultipartFile missionCompleteImage;

    @NotBlank
    private String missionCompleteContent;

    @NotNull
    private Long furnitureId;

}
