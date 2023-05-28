package com.example.demo.controller;

import com.example.demo.entity.Picture;
import com.example.demo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/pictures")
//@CrossOrigin("*")
public class PictureController {

    private static final String UPLOAD_DIR = "G:\\CODEGYM\\pictures";

    @Autowired
    private PictureService pictureService;

    @GetMapping("/all")
    public List<Picture> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @PostMapping("/upload")
    public String uploadPicture(@RequestParam("file") MultipartFile file) {
        try {
            pictureService.uploadPicture(file);
            return "Picture uploaded successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload the picture.";
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadPicture(@PathVariable String fileName) throws MalformedURLException {
        // Specify the file path
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        // Check if the file exists
        if (Files.exists(filePath)) {
            // Load the file resource
            Resource resource = new FileSystemResource(filePath.toFile());

            // Set the appropriate content type for the response
            String contentType = determineContentType(fileName);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Determine the content type based on the file extension
    private String determineContentType(String fileName) {
        // Implement your own logic to determine the content type
        // For example, you can use the file extension to map to appropriate
        // content types like image/jpeg, image/png, etc.
        return "image/jpeg";
    }
}
