package com.example.demo.service;

import com.example.demo.entity.Picture;
import com.example.demo.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class PictureService {

    private static final String UPLOAD_DIR = "G:\\CODEGYM\\pictures";

    @Autowired
    private PictureRepository pictureRepository;

    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    public void uploadPicture(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Path.of(UPLOAD_DIR, fileName);

        // Save the file to the upload directory
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Create a new Picture object and save it to the database
        Picture picture = new Picture();
        picture.setFileName(fileName);
        pictureRepository.save(picture);
    }
}
