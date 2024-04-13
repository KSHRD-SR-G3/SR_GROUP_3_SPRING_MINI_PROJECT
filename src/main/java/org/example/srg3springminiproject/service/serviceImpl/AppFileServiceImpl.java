package org.example.srg3springminiproject.service.serviceImpl;

import org.example.srg3springminiproject.service.AppFileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class AppFileServiceImpl implements AppFileService {

    //reference path to directory profile_images
    private final Path root = Paths.get("src/main/resources/profile_images");
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        //auto create directory profile_images
        if(!Files.exists(root)){
            Files.createDirectories(root);
        }
        //upload profile_image to directory
        //convert fileName to UUID because we don't want to have duplicate file name UUID help skip duplicate file
        fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
        //Insert file as byte to directory
        Files.copy(file.getInputStream(), root.resolve(fileName));
        return fileName;
    }

    @Override
    public Resource getFileByFileName(String fileName) throws IOException {
        Path path = Paths.get("src/main/resources/profile_images/" + fileName);
        return new ByteArrayResource(Files.readAllBytes(path));
    }
}
