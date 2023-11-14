package com.groom.cookiehouse.domain.cookie;

import com.groom.cookiehouse.domain.BaseEntity;
import com.groom.cookiehouse.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "COOKIE_SELECTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CookieSelection extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cookieId")
    private Cookie cookie;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public CookieSelection(Cookie cookie, User user) {
        this.cookie = cookie;
        this.user = user;
    }
}
