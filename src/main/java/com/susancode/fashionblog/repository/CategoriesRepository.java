package com.susancode.fashionblog.repository;

import com.susancode.fashionblog.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    Optional<Categories> findByTitle(String title);

}