package com.groom.cookiehouse.service.guestBook;

import com.groom.cookiehouse.controller.dto.response.guestBook.GetAllGuestBookResponseDto;
import com.groom.cookiehouse.domain.GuestBook;
import com.groom.cookiehouse.domain.user.User;
import com.groom.cookiehouse.controller.dto.request.guestBook.GuestBookRequestDto;
import com.groom.cookiehouse.controller.dto.response.guestBook.GuestBookResponseDto;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.model.NotFoundException;
import com.groom.cookiehouse.repository.GuestBookRepository;
import com.groom.cookiehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;

    @Transactional
    public GuestBookResponseDto addGuestBook(GuestBookRequestDto guestBookRequestDto) {
        User user = userRepository.findById(guestBookRequestDto.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        GuestBook guestBook = GuestBook.builder()
                .author(guestBookRequestDto.getAuthor())
                .content(guestBookRequestDto.getContent())
                .user(user)
                .ornamentId(guestBookRequestDto.getOrnamentId())
                .build();
        guestBookRepository.save(guestBook);

        return GuestBookResponseDto.of(guestBook);
    }

    @Transactional
    public GetAllGuestBookResponseDto getAllGuestBook(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        String houseName = user.getHouseName();

        List<GuestBook> guestBooks = guestBookRepository.findAllByUser(user);
        List<GuestBookResponseDto> guestBookResponseDtos = new ArrayList<>();
        for (GuestBook guestBook : guestBooks) {
            guestBookResponseDtos.add(
                    GuestBookResponseDto.of(guestBook)
            );
        }
        return GetAllGuestBookResponseDto.of(houseName, guestBookResponseDtos);
    }

}
