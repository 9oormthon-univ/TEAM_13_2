package com.groom.cookiehouse.domain.icing;

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
@Table(name = "ICING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Icing extends BaseEntity {

    @Column(nullable = true)
    private String name;

    @Builder
    public Icing(String name) {
        this.name = name;
    }

}
