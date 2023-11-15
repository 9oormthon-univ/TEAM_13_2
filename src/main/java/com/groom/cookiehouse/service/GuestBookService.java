package com.groom.cookiehouse.service;

import com.groom.cookiehouse.domain.GuestBook;
import com.groom.cookiehouse.domain.Ornament;
import com.groom.cookiehouse.domain.user.User;
import com.groom.cookiehouse.dto.GuestBookRequestDto;
import com.groom.cookiehouse.dto.GuestBookResponseDto;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.model.CustomException;
import com.groom.cookiehouse.repository.GuestBookRepository;
import com.groom.cookiehouse.repository.OrnamentRepository;
import com.groom.cookiehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestBookService {
    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;

    private final OrnamentRepository ornamentRepository;


    @Transactional
    public GuestBookResponseDto addGuestBook(GuestBookRequestDto guestBookRequestDto) {
        Optional<User> optionalUser = userRepository.findById((long) guestBookRequestDto.getUserId());
        User user = optionalUser.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION, "사용자 정보가 없습니다."));

        Optional<Ornament> optionalOrnament = ornamentRepository.findById(guestBookRequestDto.getOrnamentId());
        Ornament ornament = optionalOrnament.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION, "오너먼트 정보가 없습니다."));

        GuestBook guestBook = guestBookRequestDto.toEntity(user, ornament);
        guestBookRepository.save(guestBook);

        return GuestBookResponseDto.of(guestBook);
    }

    @Transactional
    public List<GuestBook> getAllGuestBook(){
        return guestBookRepository.findAll();
    }

}
