package com.epam.services;

import com.epam.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    void addNewPost(Post post);
}
