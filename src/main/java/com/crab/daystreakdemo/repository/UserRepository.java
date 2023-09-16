package com.crab.daystreakdemo.repository;

import com.crab.daystreakdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
