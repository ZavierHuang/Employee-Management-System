package org.example.crud.controller;

//https://github.com/codejava-official/spring-boot-crud-intellij/tree/main
import org.example.crud.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.crud.bean.User;
import org.example.crud.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;


@Controller
public class UserController {
    @Autowired  private UserService userService;

    @GetMapping("/users")
    public String showAllUser(Model model){
        List<User> userList = userService.getAllUser();
        model.addAttribute("listUsers", userList);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUserInfo(User user, RedirectAttributes redirectAttributes){
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "User Saved");
        return "redirect:/users";
    }

    @GetMapping("/users/update/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getUserInfoById(id);
            System.out.println("PASSWORD:"+user.getPassword());
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "user_form";
        }catch(UserNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try{
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (UserPrincipalNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }

}
