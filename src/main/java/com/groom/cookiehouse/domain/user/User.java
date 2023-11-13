package com.groom.cookiehouse.domain.user;

import com.groom.cookiehouse.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@DynamicInsert // null값이 아닌 필드만을 대상으로 SQL INSERT 문을 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String socialId;

    @Column(nullable = true)
    private String refreshToken;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Builder
    public User(String userName, String socialId, SocialType socialType) {
        this.userName = userName;
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}