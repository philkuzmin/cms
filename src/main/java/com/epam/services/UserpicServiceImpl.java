package com.epam.services;

import com.epam.exceptions.UserpicFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UserpicServiceImpl implements UserpicService {

    @Value("${images.path}")
    private String imagePath;

    @Override
    public Resource getUserpic(Long id) {

        try {

            Path filePath = Paths.get(imagePath, "userpics", id.toString() + ".jpg");
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;

            } else {
                Path defaultUserpic = Paths.get("src/main/resources/static/img/default-userpic.png");
                return new UrlResource(defaultUserpic.toUri());
            }

        } catch (MalformedURLException e) {
            throw new UserpicFileNotFoundException("Userpic not found");
        }
    }

    @Override
    public String uploadUserpic(MultipartFile file, Long userId) {

        if (!file.isEmpty()) {

            try {

                String fileName = imagePath + "/userpics/" + userId + ".jpg";
                Files.copy(file.getInputStream(), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);

                return fileName;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
