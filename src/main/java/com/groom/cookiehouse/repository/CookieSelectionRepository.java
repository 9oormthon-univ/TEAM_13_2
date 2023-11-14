package com.groom.cookiehouse.repository;

import com.groom.cookiehouse.domain.cookie.CookieSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieSelectionRepository extends JpaRepository<CookieSelection, Long> {
}
