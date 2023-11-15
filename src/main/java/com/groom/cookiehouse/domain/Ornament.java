package com.groom.cookiehouse.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ORNAMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ornament extends BaseEntity {

    @Column
    private String name;

    @Builder
    public Ornament(String name) {
        this.name = name;
    }

}
