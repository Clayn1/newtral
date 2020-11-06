package com.clayn.newtral.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int views;
    private int likes;
    private int dislikes;
    private float popularity;
    @NotBlank
    @NotNull
    private String title;
    private String htmlTemplate;
    private String imageURL;
    private String section;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private UserPage user;
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<Comment> comments;
}
