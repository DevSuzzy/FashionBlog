package com.susancode.fashionblog.repository;

import com.susancode.fashionblog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {



//      List<Post> findAllByCategories_Title(String categoryTitle);

      Page<Post> findAllByCategories_Title(String categoryTitle, Pageable pageable);

}