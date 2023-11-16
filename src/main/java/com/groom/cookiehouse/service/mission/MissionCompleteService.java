package com.groom.cookiehouse.service.mission;

import com.groom.cookiehouse.controller.dto.request.mission.MissionCompleteRequestDto;
import com.groom.cookiehouse.controller.dto.response.mission.MissionCompleteResponseDto;
import com.groom.cookiehouse.domain.mission.Mission;
import com.groom.cookiehouse.domain.mission.MissionComplete;
import com.groom.cookiehouse.domain.user.User;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.model.NotFoundException;
import com.groom.cookiehouse.repository.MissionCompleteRepository;
import com.groom.cookiehouse.repository.MissionRepository;
import com.groom.cookiehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MissionCompleteService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final MissionCompleteRepository missionCompleteRepository;

    @Transactional
    public MissionCompleteResponseDto createMissionComplete(MissionCompleteRequestDto requestDto, Long userId, String imageUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Mission mission = missionRepository.findByDate(LocalDate.now())
                .orElseThrow(() ->new NotFoundException(ErrorCode.NOT_FOUND_MISSION_EXCEPTION, ErrorCode.NOT_FOUND_MISSION_EXCEPTION.getMessage()));

        MissionComplete missionComplete = MissionComplete.builder()
                .image(imageUrl)
                .content(requestDto.getMissionCompleteContent())
                .mission(mission)
                .user(user)
                .furnitureId(requestDto.getFurnitureId())
                .build();
        missionCompleteRepository.save(missionComplete);

        return MissionCompleteResponseDto.of(missionComplete.getId(), missionComplete.getCreatedAt(), missionComplete.getUpdatedAt());
    }


}
