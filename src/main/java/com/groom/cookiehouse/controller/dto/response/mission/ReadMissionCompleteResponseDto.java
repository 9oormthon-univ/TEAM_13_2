package com.groom.cookiehouse.controller.dto.response.mission;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadMissionCompleteResponseDto {

    private Long missionCompleteId;
    private String missionCompleteImage;
    private String missionCompleteContent;
    private LocalDate missionCompleteDate;
    private Long missionCompleteFurnitureId;

    public static ReadMissionCompleteResponseDto of(
            Long missionCompleteId,
            String missionCompleteImage,
            String missionCompleteContent,
            LocalDate missionCompleteDate,
            Long missionCompleteFurnitureId
    ) {
        return new ReadMissionCompleteResponseDto(
                missionCompleteId,
                missionCompleteImage,
                missionCompleteContent,
                missionCompleteDate,
                missionCompleteFurnitureId
        );
    }

}
