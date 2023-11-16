package com.groom.cookiehouse.domain;

import com.groom.cookiehouse.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "GUEST_BOOK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestBook extends BaseEntity{

    @Column
    private String author;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Long ornamentId;

    @Builder
    public GuestBook(String author, String content, User user, Long ornamentId) {
        this.author = author;
        this.content = content;
        this.user = user;
        this.ornamentId = ornamentId;
    }
}
