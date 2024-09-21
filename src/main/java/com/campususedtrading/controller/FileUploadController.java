package com.campususedtrading.controller;

import com.campususedtrading.pojo.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("C:\\Users\\MorningStar\\Desktop\\files\\"+filename));
        return Result.success(file.toString());
    }
}
