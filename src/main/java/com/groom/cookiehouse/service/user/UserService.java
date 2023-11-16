package com.groom.cookiehouse.service.user;

import com.groom.cookiehouse.controller.dto.response.user.UserResponseDto;
import com.groom.cookiehouse.domain.mission.Mission;
import com.groom.cookiehouse.domain.user.User;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.model.NotFoundException;
import com.groom.cookiehouse.repository.MissionCompleteRepository;
import com.groom.cookiehouse.repository.MissionRepository;
import com.groom.cookiehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final MissionCompleteRepository missionCompleteRepository;

    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Boolean isHouseBuilt = true;
        if (user.getHouseName() == null) {
            isHouseBuilt = false;
        }
        List<Mission> missions = missionRepository.findAllByDate(LocalDate.now());
        Mission mission = missions.get(0);
        Boolean todayMissionComplete = missionCompleteRepository.existsByUserAndMission(user, mission);
        return UserResponseDto.of(userId, user.getUserName(), isHouseBuilt, todayMissionComplete);
    }

}
