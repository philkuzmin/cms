package com.epam.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UserpicService {

    Resource getUserpic(Long id);
    String uploadUserpic(MultipartFile file, Long userId);
}
