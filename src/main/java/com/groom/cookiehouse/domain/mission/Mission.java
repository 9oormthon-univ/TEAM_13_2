package com.groom.cookiehouse.domain.mission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groom.cookiehouse.domain.BaseEntity;
import com.groom.cookiehouse.domain.furniture.Furniture;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Table(name = "MISSION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Column(nullable = true)
    private String message;

    @Column(nullable = false)
    private LocalDate date;

    @JsonIgnore
    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY)
    List<Furniture> furnitures;

    @Builder
    public Mission(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }

}
