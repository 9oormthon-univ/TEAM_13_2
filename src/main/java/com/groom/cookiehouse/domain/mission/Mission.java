package com.groom.cookiehouse.domain.mission;

import com.groom.cookiehouse.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "MISSION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Column(nullable = true)
    private String message;

    @Column(nullable = false)
    private LocalDate date;

    @Builder
    public Mission(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }

}
