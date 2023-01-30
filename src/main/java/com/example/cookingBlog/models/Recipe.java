package com.example.cookingBlog.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Indexed
@Table(name = "recipes_table")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recipe_id")
    private Long id;
    @Column(name = "recipe_title")
//    @FullTextField
    private String title;
    //    @FullTextField
    @Column(columnDefinition = "text")
    private String recipe_text;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comment;

    private double rating;

    @OneToOne(mappedBy = "recipe", cascade = CascadeType.REFRESH)
    @JoinColumn(name = "image_id")
    private Image image;


    private LocalDateTime dateOfCreate;

    @PrePersist
    private void init() {
        dateOfCreate = LocalDateTime.now();
        rating = 0.0;
        countOfVotes = 0;
        scores = 0;
    }

    private double countOfVotes;
    private double scores;

}
