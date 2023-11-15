package com.groom.cookiehouse.repository;

import com.groom.cookiehouse.domain.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository <GuestBook, Integer> {

}
