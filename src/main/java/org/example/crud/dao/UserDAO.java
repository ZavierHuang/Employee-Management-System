package org.example.crud.dao;

import org.example.crud.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<User, Long> {
}
