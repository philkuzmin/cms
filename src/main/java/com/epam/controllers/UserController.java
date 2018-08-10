package com.epam.controllers;

import com.epam.dto.UserDto;
import com.epam.security.UserDetailsImpl;
import com.epam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Authentication authentication, ModelMap model) {

        if (authentication != null) {

            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
            List<UserDto> users = userService.getAllUsers().stream()
                    .map(UserDto::from)
                    .collect(Collectors.toList());

            model.addAttribute("user", UserDto.from(principal.getUser()));
            model.addAttribute("users", users);
            return "user-list";
        }
        return "/";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(Long.parseLong(id));
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/restore")
    public String restoreUser(@PathVariable("id") String id) {
        userService.restoreUser(Long.parseLong(id));
        return "redirect:/users";
    }
}
