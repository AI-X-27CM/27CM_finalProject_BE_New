package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class AudioController {

    @PostMapping("/upload-audio")
    public String uploadAudio(@RequestParam("audioFile") MultipartFile audioFile) {
        try {
            if (audioFile.isEmpty()) {
                return "File upload failed: Audio file is empty";
            }

            String desktopPath = System.getProperty("user.home") + "\\Desktop\\record";
            String fileName = desktopPath + "\\" + audioFile.getOriginalFilename();

            if (!isValidFileName(audioFile.getOriginalFilename())) {
                return "File upload failed: Invalid file name";
            }

            File file = new File(fileName);
            File directory = new File(desktopPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            audioFile.transferTo(file);
            return "File uploaded successfully: " + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed: " + e.getMessage();
        }
    }

    private boolean isValidFileName(String fileName) {
        // 정규표현식을 사용한 파일명 유효성 검사
        return fileName.matches("^[a-zA-Z0-9._-]+$");
    }
}
