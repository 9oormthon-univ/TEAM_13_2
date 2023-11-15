package com.groom.cookiehouse.repository;

import com.groom.cookiehouse.domain.mission.MissionComplete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionCompleteRepository extends JpaRepository<MissionComplete, Long> {
}
