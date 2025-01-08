package org.example.crud;

import org.assertj.core.api.Assertions;
import org.example.crud.bean.User;
import org.example.crud.dao.UserDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserDAOTests {

    @Autowired private UserDAO userDAO;

    @Test
    public void testFindAllUser(){
        Iterable<User> users = userDAO.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        System.out.println(users);
    }

    @Test
    public void testGetUserId(){
        int userId = 1;
        Optional<User> optionalUser = userDAO.findById((long) userId);
        // isPresent : if result is null, then return True
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println("user get:" + optionalUser.get());
    }

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("alex.stevenson@gmail.com");
        user.setPassword("alex123456");
        user.setFirstName("Alex");
        user.setLastName("Stevenson");
        user.setEnabled("True");

        User savedUser = userDAO.save(user);

        System.out.println(savedUser);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testUpdate() {
        int userId = 1;
        Optional<User> getUser = userDAO.findById((long) userId);
        User user = getUser.get();
        user.setEmail("123@gmail.com");
        user.setPassword("123123456");
        userDAO.save(user);

        Assertions.assertThat(userDAO.findById((long)userId).get().getPassword())
                .isEqualTo("123123456");
    }

    @Test
    public void testDelete() {
        int userId = 2;
        userDAO.deleteById((long) userId);
        Assertions.assertThat(userDAO.findById((long)userId)).isNotPresent();
    }

}
