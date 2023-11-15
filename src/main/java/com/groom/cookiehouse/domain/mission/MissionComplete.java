package com.groom.cookiehouse.domain.mission;

import com.groom.cookiehouse.domain.BaseEntity;
import com.groom.cookiehouse.domain.furniture.Furniture;
import com.groom.cookiehouse.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MISSION_COMPLETE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionComplete extends BaseEntity {

    @Column(nullable = false)
    private String image;

    @Column(length = 500, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "missionId")
    private Mission mission;

    @ManyToOne
    @JoinColumn(name = "furnitureId")
    private Furniture furniture;

    @Builder
    public MissionComplete(String image, String content, User user, Mission mission, Furniture furniture) {
        this.image = image;
        this.content = content;
        this.user = user;
        this.mission = mission;
        this.furniture = furniture;
    }
}
