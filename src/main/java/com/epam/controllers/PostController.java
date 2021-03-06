package com.epam.controllers;

import com.epam.dto.PostDto;
import com.epam.dto.UserDto;
import com.epam.model.Post;
import com.epam.model.User;
import com.epam.security.UserDetailsImpl;
import com.epam.services.PostServiceImpl;
import com.epam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showAllPosts(Authentication authentication, ModelMap model, Pageable pageable) {

        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            UserDto user = UserDto.from(userDetails.getUser());
            model.addAttribute("user", user);
        }

        pageable = PageRequest.of(pageable.getPageNumber(), 5, Sort.by("id").descending());

        Page<Post> page = postService.getPostsPage(pageable);
        model.addAttribute("page", page);
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

    @GetMapping("/postsBy")
    public String getAllPostsByUser(@RequestParam("id") String id,
                                    Authentication authentication, ModelMap model, Pageable pageable) {

        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            UserDto user = UserDto.from(userDetails.getUser());
            model.addAttribute("user", user);
        }

        User requestedUser = userService.getUserById(Long.parseLong(id));

        if (requestedUser != null) {

            pageable = PageRequest.of(pageable.getPageNumber(), 5, Sort.by("id").descending());
            Page<Post> page = postService.getAllPostsByUser(Long.parseLong(id), pageable);
            model.addAttribute("page", page);
            model.addAttribute("requestedUser", requestedUser);
        }
        return "posts";
    }
}
