package com.example.cookingBlog.models;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blog_table")
public class Blog {

    @Id
    @Column(name = "blog_id")
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "blog_title")
    private String title;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    private List<Recipe> recipesList;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @PrePersist
    void defTitle(){
        if (this.title == null)
            this.title = this.account.getFirstName();
    }


}
