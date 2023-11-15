package com.groom.cookiehouse.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter

public class Ornament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="guest_book_id")
    private GuestBook guestBook;
}
