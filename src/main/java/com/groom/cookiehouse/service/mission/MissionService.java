package com.groom.cookiehouse.service.mission;

import com.groom.cookiehouse.controller.dto.response.mission.MissionResponseDto;
import com.groom.cookiehouse.domain.mission.Mission;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.model.NotFoundException;
import com.groom.cookiehouse.repository.MissionRepository;
import com.groom.cookiehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @PostConstruct
    private void initMissions() {
        // 미션 메세지 (수정 예정)
        List<String> missionMessages = new ArrayList<>();
        missionMessages.add("미션 메세지1");
        missionMessages.add("미션 메세지2");
        missionMessages.add("미션 메세지3");
        missionMessages.add("미션 메세지4");
        missionMessages.add("미션 메세지5");
        missionMessages.add("미션 메세지6");
        missionMessages.add("미션 메세지7");
        missionMessages.add("미션 메세지8");
        missionMessages.add("미션 메세지9");
        missionMessages.add("미션 메세지10");
        missionMessages.add("미션 메세지11");
        missionMessages.add("미션 메세지12");
        missionMessages.add("미션 메세지13");
        missionMessages.add("미션 메세지14");
        missionMessages.add("미션 메세지15");
        missionMessages.add("미션 메세지16");
        missionMessages.add("미션 메세지17");
        missionMessages.add("미션 메세지18");
        missionMessages.add("미션 메세지19");
        missionMessages.add("미션 메세지20");
        missionMessages.add("미션 메세지21");
        missionMessages.add("미션 메세지22");
        missionMessages.add("미션 메세지23");
        missionMessages.add("미션 메세지24");
        missionMessages.add("미션 메세지25");
        missionMessages.add("미션 메세지26");
        missionMessages.add("미션 메세지27");
        missionMessages.add("미션 메세지28");
        missionMessages.add("미션 메세지29");
        missionMessages.add("미션 메세지30");
        missionMessages.add("미션 메세지31");
        missionMessages.add("미션 메세지32");
        missionMessages.add("미션 메세지33");
        missionMessages.add("미션 메세지34");
        missionMessages.add("미션 메세지35");
        missionMessages.add("미션 메세지36");
        missionMessages.add("미션 메세지37");
        missionMessages.add("미션 메세지38");
        missionMessages.add("미션 메세지39");
        missionMessages.add("미션 메세지40");
        missionMessages.add("미션 메세지41");
        missionMessages.add("미션 메세지42");
        missionMessages.add("미션 메세지43");
        missionMessages.add("미션 메세지44");
        missionMessages.add("미션 메세지45");
        missionMessages.add("미션 메세지46");
        missionMessages.add("미션 메세지47");
        missionMessages.add("미션 메세지48");
        missionMessages.add("미션 메세지49");

        LocalDate christmas = LocalDate.of(2023, 12, 25);
        long remainDays = LocalDate.now().until(christmas, ChronoUnit.DAYS);
        for (long i = 0; i <= remainDays; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            Mission mission = Mission.builder().message(missionMessages.get((int)i)).date(date).build();
            missionRepository.save(mission);
        }
    }

    public MissionResponseDto getTodayMission(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Mission mission = missionRepository.findByDate(LocalDate.now())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MISSION_EXCEPTION, ErrorCode.NOT_FOUND_MISSION_EXCEPTION.getMessage()));

        return MissionResponseDto.of(
                mission.getId(),
                mission.getDate(),
                mission.getMessage()
        );
    }

}
