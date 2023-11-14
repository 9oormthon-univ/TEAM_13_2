package com.groom.cookiehouse.domain.cookie;

import com.groom.cookiehouse.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "COOKIE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cookie extends BaseEntity {

    @Column(nullable = true)
    private String name;

    @Builder
    public Cookie(String name) {
        this.name = name;
    }

}
