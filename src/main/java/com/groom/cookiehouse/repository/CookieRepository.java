package com.groom.cookiehouse.repository;

import com.groom.cookiehouse.domain.cookie.Cookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieRepository extends JpaRepository<Cookie, Long> {
}
