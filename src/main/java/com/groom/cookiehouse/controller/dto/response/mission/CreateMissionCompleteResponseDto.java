package com.groom.cookiehouse.controller.dto.response.mission;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMissionCompleteResponseDto {

    private Long missionCompleteId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CreateMissionCompleteResponseDto of(Long missionCompleteId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new CreateMissionCompleteResponseDto(missionCompleteId, createdAt, updatedAt);
    }

}
