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
@Table(name = "admins")
public class Admin extends BaseEntity  {

        @Column(nullable = false, length = 100)
        private String name;

        @Column(nullable = false, length = 50)
        private String password;

        @Column(nullable = false)
        private String email;

        private AuthorType authorType = AuthorType.ADMIN;

        @OneToMany(cascade=CascadeType.ALL, mappedBy = "admin", fetch = FetchType.EAGER)
        private List<Post> postList  = new ArrayList<Post>();

        @OneToMany(cascade=CascadeType.ALL, mappedBy = "admin", fetch = FetchType.EAGER)
        private List<Comment> commentList  = new ArrayList<Comment>();

    }
