package com.groom.cookiehouse.controller.dto.response.mission;

import com.groom.cookiehouse.domain.furniture.Furniture;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionResponseDto {

    private Long missionId;
    private LocalDate missionDate;
    private String missionMessage;
    private List<Long> todayFurnitureIds;

    public static MissionResponseDto of(Long missionId, LocalDate missionDate, String missionMessage, List<Long> todayFurnitureIds) {
        return new MissionResponseDto(missionId, missionDate, missionMessage, todayFurnitureIds);
    }

}
