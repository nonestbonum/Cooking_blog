package com.example.cookingBlog.models;


import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_table")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(value = EnumType.STRING)
    private Role role;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Blog blog;
    @Column(name = "is_banned")
    private boolean isBanned;

    @PrePersist
    private void prePer() {
        this.isBanned = false;
    }
}
