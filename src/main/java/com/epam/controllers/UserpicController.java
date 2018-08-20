package com.epam.controllers;

import com.epam.model.User;
import com.epam.security.UserDetailsImpl;
import com.epam.services.UserpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserpicController {

    @Autowired
    private UserpicService userpicService;

    @PostMapping("uploadUserpic")
    public String uploadUserpic(@RequestParam MultipartFile file, Authentication authentication) {

        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        String fileName = userpicService.uploadUserpic(file, user.getId());
        return "redirect:/user";
    }

    @GetMapping("userpic/{userId}")
    @ResponseBody
    public ResponseEntity<Resource> getUserpic(@PathVariable Long userId) {

        Resource resource = userpicService.getUserpic(userId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
