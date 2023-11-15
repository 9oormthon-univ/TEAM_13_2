package com.groom.cookiehouse.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groom.cookiehouse.domain.BaseEntity;
import com.groom.cookiehouse.domain.GuestBook;
import lombok.*;
import com.groom.cookiehouse.domain.cookie.CookieSelection;
import com.groom.cookiehouse.domain.icing.IcingSelection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@DynamicInsert // null값이 아닌 필드만을 대상으로 SQL INSERT 문을 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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


    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<GuestBook> guestBookList;
  
    @Column(nullable = true)
    private String houseName;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<IcingSelection> icingSelections;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<CookieSelection> cookieSelections;

    @Builder
    public User(String userName, String socialId, SocialType socialType) {
        this.userName = userName;
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public void updateHouseName(String houseName) {
        this.houseName = houseName;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}