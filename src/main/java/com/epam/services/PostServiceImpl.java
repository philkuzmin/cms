package com.epam.services;

import com.epam.model.Post;
import com.epam.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc();
    }

    @Override
    public void addNewPost(Post post) {
        postRepository.saveAndFlush(post);
    }

    @Override
    public Page<Post> getPostsPage(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> getAllPostsByUser(Long id, Pageable pageable) {
        return postRepository.findAllByUserIdOrderByIdDesc(id, pageable);
    }
}
