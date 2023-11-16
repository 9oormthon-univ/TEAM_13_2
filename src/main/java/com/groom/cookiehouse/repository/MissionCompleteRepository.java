package com.groom.cookiehouse.repository;

import com.groom.cookiehouse.domain.mission.Mission;
import com.groom.cookiehouse.domain.mission.MissionComplete;
import com.groom.cookiehouse.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MissionCompleteRepository extends JpaRepository<MissionComplete, Long> {

    Optional<MissionComplete> findByUserAndMission(User user, Mission mission);

    Boolean existsByUserAndMission(User user, Mission mission);

    List<MissionComplete> findAllByUser(User user);

}
