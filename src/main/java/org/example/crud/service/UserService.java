package org.example.crud.service;

import org.example.crud.UserNotFoundException;
import org.example.crud.bean.User;
import org.example.crud.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserDAO userDAO;

    public List<User> getAllUser(){
        return (List<User>)userDAO.findAll();
    }

    public void save(User user){
        userDAO.save(user);
    }

    public void delete(Integer userId) throws UserPrincipalNotFoundException {
        if(userDAO.findById((long) userId).isPresent())
            userDAO.deleteById((long) userId);
        else
            throw new UserPrincipalNotFoundException("Could not find any users with ID " + userId);
    }

    public User getUserInfoById(Integer userId) throws UserNotFoundException {
        Optional<User> result = userDAO.findById((long)userId);
        if (result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + userId);
    }
}