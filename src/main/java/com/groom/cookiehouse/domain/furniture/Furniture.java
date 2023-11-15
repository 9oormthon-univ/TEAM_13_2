package com.groom.cookiehouse.domain.furniture;

import com.groom.cookiehouse.domain.BaseEntity;
import com.groom.cookiehouse.domain.mission.Mission;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "FURNITURE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Furniture extends BaseEntity {

    @Column(nullable = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "missionId")
    private Mission mission;

    @Builder
    public Furniture(String name, Mission mission) {
        this.name = name;
        this.mission = mission;
    }
}
