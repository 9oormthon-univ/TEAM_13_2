package com.groom.cookiehouse.service.house;

import com.groom.cookiehouse.controller.dto.response.house.HouseResponseDto;
import com.groom.cookiehouse.domain.cookie.Cookie;
import com.groom.cookiehouse.domain.cookie.CookieSelection;
import com.groom.cookiehouse.domain.icing.Icing;
import com.groom.cookiehouse.domain.icing.IcingSelection;
import com.groom.cookiehouse.domain.user.User;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.model.NotFoundException;
import com.groom.cookiehouse.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final UserRepository userRepository;
    private final IcingRepository icingRepository;
    private final CookieRepository cookieRepository;
    private final CookieSelectionRepository cookieSelectionRepository;
    private final IcingSelectionRepository icingSelectionRepository;

    @PostConstruct
    private void initData() {
        String[] cookieNames = {"하트", "머랭", "크래커", "눈꽃", "후렌치파이", "프레첼"};
        String[] icingNames = {"아이싱1", "아이싱2", "아이싱3", "아이싱4", "아이싱5", "아이싱6"};
        for (String cookieName : cookieNames) {
            cookieRepository.save(
                    Cookie.builder().name(cookieName).build()
            );
        }
        for (String icingName : icingNames) {
            icingRepository.save(
                    Icing.builder().name(icingName).build()
            );
        }
    }

    @Transactional
    public void createHouse(Long userId, Long icingId, List<Long> cookieIds, String houseName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Icing icing = icingRepository.findById(icingId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ICING_EXCEPTION, ErrorCode.NOT_FOUND_ICING_EXCEPTION.getMessage()));
        for (Long cookieId : cookieIds) {
            Cookie cookie = cookieRepository.findById(cookieId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_COOKIE_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
            // 쿠키 선택 레코드 생성
            cookieSelectionRepository.save(
                    CookieSelection.builder()
                            .cookie(cookie)
                            .user(user)
                            .build()
            );
        }
        // 아이싱 선택 레코드 생성
        icingSelectionRepository.save(
                IcingSelection.builder()
                        .user(user)
                        .icing(icing)
                        .build()
        );
        user.updateHouseName(houseName);
    }

    public HouseResponseDto getHouse(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        List<IcingSelection> icingSelections = user.getIcingSelections();
        List<CookieSelection> cookieSelections = user.getCookieSelections();

        List<Long> cookieIds = new ArrayList<>();
        if (!cookieSelections.isEmpty()) {
            for (CookieSelection cookieSelection : cookieSelections) {
                cookieIds.add(cookieSelection.getCookie().getId());
            }
        }
        Long icingId = null;
        if (!icingSelections.isEmpty()) {
            icingId = icingSelections.get(0).getIcing().getId();
        }

        return HouseResponseDto.of(
                icingId,
                cookieIds,
                user.getHouseName()
        );
    }

}
