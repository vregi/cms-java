package com.slezkipotekli.cms.repository;

import com.slezkipotekli.cms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author vregi, 11/24/2024
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
