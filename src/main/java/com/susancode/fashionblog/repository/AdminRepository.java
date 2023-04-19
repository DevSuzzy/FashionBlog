package com.susancode.fashionblog.repository;

import com.susancode.fashionblog.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String email);

    Optional<Object> findByEmailAndPassword(String email, String password);
}
