package com.groom.cookiehouse.repository;

import com.groom.cookiehouse.domain.icing.Icing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IcingRepository extends JpaRepository<Icing, Long> {
}
