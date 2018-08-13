package com.epam.services;

import com.epam.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    void addNewPost(Post post);
    Page<Post> getPostsPage(Pageable pageable);
}
