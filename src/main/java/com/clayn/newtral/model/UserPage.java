package com.clayn.newtral.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer level;
    private Integer reputation;
    @NotBlank
    @NotNull
    @Column(unique = true)
    @Length(min = 4)
    private String username;
    private String description;
    private String htmlTemplate;
    private String imageURL;
    @Temporal(TemporalType.DATE)
    private Date registrationDate = new Date();

    @OneToOne(mappedBy = "userPage")
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
    @OneToMany(mappedBy = "userPage")
    @JsonIgnore
    private List<Comment> comments;
}
