package com.susancode.fashionblog.repository;

import com.susancode.fashionblog.entity.Comment;
import com.susancode.fashionblog.entity.Post;
import com.susancode.fashionblog.entity.Visitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByPost(Post post);


    Page<Comment> findAllByVisitor(Visitor visitor, Pageable pageable);

    Page<Comment> findAllByPost(Post post, Pageable pageable);
}
