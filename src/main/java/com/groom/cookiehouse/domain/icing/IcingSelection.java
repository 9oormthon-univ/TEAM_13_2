package com.groom.cookiehouse.domain.icing;

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
@Table(name = "ICING_SELECTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IcingSelection extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "icingId")
    private Icing icing;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public IcingSelection(Icing icing, User user) {
        this.icing = icing;
        this.user = user;
    }
}
