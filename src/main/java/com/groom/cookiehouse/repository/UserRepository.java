package com.groom.cookiehouse.repository;

import com.groom.cookiehouse.domain.user.SocialType;
import com.groom.cookiehouse.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsBySocialIdAndSocialType(String socialId, SocialType socialType);
    Optional<User> findBySocialIdAndSocialType(String socialId, SocialType socialType);
    Optional<User> findByRefreshToken(String refreshToken);

}
