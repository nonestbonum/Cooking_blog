package com.example.cookingBlog.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "images_table")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long id;
    @Column(name = "image_name")
    private String name;
    @Column(name = "image_content_type")
    private String type;
    @Lob
    private byte[] data;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Image(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
