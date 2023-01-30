package com.example.cookingBlog.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment_table")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    private String blog_title;

    @PrePersist
    void def() {
        this.blog_title = this.blog.getTitle();
        dateOfCreate = LocalDateTime.now();
    }

    @Column(columnDefinition = "text")
    private String comment_text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Recipe recipe;

    private LocalDateTime dateOfCreate;


    public Comment(Blog blog, String comment_text, Recipe recipe) {
        this.blog = blog;
        this.comment_text = comment_text;
        this.recipe = recipe;
    }
}
