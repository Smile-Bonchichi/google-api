package com.test.testParserIdCard.controller;

import com.test.testParserIdCard.entity.File;
import com.test.testParserIdCard.service.FileService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileController {
    final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public File save(@RequestParam("file") MultipartFile file) {
        return fileService.saveFile(file);
    }
}
