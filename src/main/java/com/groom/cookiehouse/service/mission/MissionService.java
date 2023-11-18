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
        if (missionRepository.findAll().size() == 0) {
            List<String> missionMessages = new ArrayList<>();
            missionMessages.add("구름톤에서 불태웠던 열정!");
            missionMessages.add("가족과 함께 찍은 행복한 순간올리기");
            missionMessages.add("가장 편안하게 나의 본 모습이 나오는 친구와 추억 올리기");
            missionMessages.add("티비보면서 뒹굴뒹굴했던 순간 올리기");
            missionMessages.add("내 앨범 best 사진은?");
            missionMessages.add("눈 오는 날 사진 올리기");
            missionMessages.add("기억 남는 여행추억 올리기");
            missionMessages.add("친구/가족/연인에게 감동 받은 순간은?");
            missionMessages.add("자연에서의 추억 올리기");
            missionMessages.add("밤 늦게 놀았던 추억은?");
            missionMessages.add("힐링했던 순간은?");
            missionMessages.add("내가 제일 좋아하는 것은?");
            missionMessages.add("친구랑 찍은 거울셀카 best 샷");
            missionMessages.add("내가 가장 멋졌던 날");
            missionMessages.add("우울했던 날은?");
            missionMessages.add("봄 나들이 사진 올리기");
            missionMessages.add("여름의 추억 올리기");
            missionMessages.add("가장 인상깊게 읽은 책은?");
            missionMessages.add("올해 최고의 모먼트 top1?");
            missionMessages.add("작업하느라 집중했던 순간은?");
            missionMessages.add("올해 최고의 음식은?");
            missionMessages.add("가족과 먹은 음식 중 제일 맛있었던 것 올리기");
            missionMessages.add("가을의 추억 올리기");
            missionMessages.add("내가 요리해 먹은 음식 best");
            missionMessages.add("가상 세계에서 하루를 보낼 수 있다면?");
            missionMessages.add("학창 시절 가장 좋아했던 과목은?");
            missionMessages.add("가족과 함께한 가장 기억에 남은 휴가?");
            missionMessages.add("재미있게 본 콘서트?");
            missionMessages.add("어릴 적 가장 좋아했던 장난감이나 놀이는");
            missionMessages.add("처음으로 책임감을 느꼈던 순간은?");
            missionMessages.add("학교나 직장에서 가장 자랑스러웠던 순간");
            missionMessages.add("어린 시절의 친구들과의 가장 재밌었던 추억은?");
            missionMessages.add("첫 사랑의 추억은 어떠한가요");
            missionMessages.add("가장 기억에 남는 생일 파티는 어떤 것이었나요");
            missionMessages.add("처음으로 자신만의 돈을 벌었을 때 어떤 느낌이었나요?");
            missionMessages.add("어린 시절 살던 집이나 마을에 대한 기억은 어떤가요?");
            missionMessages.add("처음으로 해 본 모험은 무엇이었나요?");
            missionMessages.add("가장 기억에 남는 선물은 무엇이었나요?");
            missionMessages.add("어린 시절의 가장 큰 꿈은 무엇이었나요?");
            missionMessages.add("가족과 특별한 전통이나 행사가 있었나요?");
            missionMessages.add("대학교 첫날의 추억?");
            missionMessages.add("처음으로 여행을 갔을 때의 느낌은 어떠했나요?");
            missionMessages.add("가장 기억에 남는 크리스마스?");
            missionMessages.add("가장 좋아하는 동물은?");
            missionMessages.add("가장 좋아하는 계절은?");
            missionMessages.add("나의 소중한 취미");
            missionMessages.add("좋아하는 색깔은 무엇인가요?");
            missionMessages.add("가장 맛있었던 카페");
            missionMessages.add("메리 크리스마스! 그동안 저희 서비스를 이용해 주셔서 감사합니다.");

            LocalDate christmas = LocalDate.of(2023, 12, 25);
            long remainDays = LocalDate.now().until(christmas, ChronoUnit.DAYS);
            for (long i = 0; i <= remainDays; i++) {
                LocalDate date = LocalDate.now().plusDays(i);
                Mission mission = Mission.builder().message(missionMessages.get((int)i)).date(date).build();
                missionRepository.save(mission);
            }
        }
    }

    public MissionResponseDto getTodayMission(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        List<Mission> missions = missionRepository.findAllByDate(LocalDate.now());
        Mission mission = missions.get(0);

        return MissionResponseDto.of(
                mission.getId(),
                mission.getDate(),
                mission.getMessage()
        );
    }

}
