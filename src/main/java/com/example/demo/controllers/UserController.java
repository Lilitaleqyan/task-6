package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/createUser")
    public String createUser(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam("first_name") String first_name,
                             @RequestParam("last_name") String last_name,
                             @RequestParam("age") int age,
                             @RequestParam("email") String email,
                             ModelMap model
    ) {
        User user = new User();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setAge(age);
        user.setEmail(email);
        userService.createUser(user);

        model.addAttribute("user", userService.getByUser(user));
        return "createUser";

    }

    @GetMapping("/readUsers")
    public String readUsers(ModelMap model) {

        model.addAttribute("users", userService.readUser());

        return "readUsers";
    }


    @GetMapping("/updateUsers")
    public String updateUsers() {
        return "updateUsers";
    }

    @PostMapping("updateUsers")
    public String uptadeUsers(@RequestParam("first_name") String first_name,
                              @RequestParam("last_name") String last_name,
                              @RequestParam("email") String email,
                              @RequestParam("age") int age,

                              ModelMap model) {
        User user = new User();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setAge(age);
        user.setEmail(email);
        userService.updateUser(1, first_name, last_name, age, email);
        model.addAttribute("user", user);
        return "updateUsers";
    }

    @GetMapping("/deleteUser")
    public String deleteUser() {
        return "deleteUser";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") long id,
                             ModelMap model) {
        if (userService.dropUser(id)) {
            model.addAttribute("massage", "User deleted successfully!");

        } else {
            model.addAttribute("error", "User could not be deleted!");
        }
        return "deleteUser";
    }
}
