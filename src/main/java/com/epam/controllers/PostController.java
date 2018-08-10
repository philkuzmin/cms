package com.epam.controllers;

import com.epam.dto.PostDto;
import com.epam.dto.UserDto;
import com.epam.model.Post;
import com.epam.security.UserDetailsImpl;
import com.epam.services.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/")
    public String showAllPosts(Authentication authentication, ModelMap model) {

        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            UserDto user = UserDto.from(userDetails.getUser());
            model.addAttribute("user", user);
        }

        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/newPost")
    public String getNewPostPage(Authentication authentication, ModelMap model) {

        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            UserDto user = UserDto.from(userDetails.getUser());
            model.addAttribute("user", user);
        }

        return "new-post";
    }

    @PostMapping("/newPost")
    public String addNewPost(PostDto postDto, Authentication authentication) {

        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Post post = new Post();
            post.setHeader(postDto.getTitle());
            post.setContent(postDto.getText());
            post.setUser(userDetails.getUser());
            postService.addNewPost(post);
        }
        return "redirect:/";
    }
}
