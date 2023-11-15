package com.groom.cookiehouse.repository;

import com.groom.cookiehouse.domain.GuestBook;
import com.groom.cookiehouse.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestBookRepository extends JpaRepository <GuestBook, Integer> {

    List<GuestBook> findAllByUser(User user);

}
