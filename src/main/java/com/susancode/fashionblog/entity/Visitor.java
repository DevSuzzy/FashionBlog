package com.susancode.fashionblog.entity;

import com.susancode.fashionblog.constants.AuthorType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Visitor extends BaseEntity  {

    @Column(nullable = false)
    private String fullName;


    private AuthorType authorType = AuthorType.VISITOR;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;



    @OneToMany(cascade=CascadeType.ALL, mappedBy = "visitor", fetch = FetchType.EAGER)
    private List<Likes> likesList  = new ArrayList<Likes>();


    @OneToMany(cascade=CascadeType.ALL, mappedBy = "visitor", fetch = FetchType.EAGER)
    private List<Comment> commentList  = new ArrayList<Comment>();
}
