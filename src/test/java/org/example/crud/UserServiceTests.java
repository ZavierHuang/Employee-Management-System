package org.example.crud;

import org.assertj.core.api.Assertions;
import org.example.crud.bean.User;
import org.example.crud.dao.UserDAO;
import org.example.crud.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserServiceTests {

    @Autowired private UserService userService;

    @Test
    public void testGetAllUser(){
        Iterable<User> users = userService.getAllUser();
        System.out.println(users);
        Assertions.assertThat(users).hasSizeGreaterThan(0);
    }
}
