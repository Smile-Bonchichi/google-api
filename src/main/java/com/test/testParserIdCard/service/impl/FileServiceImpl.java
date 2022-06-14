package com.test.testParserIdCard.service.impl;

import com.test.testParserIdCard.entity.File;
import com.test.testParserIdCard.repo.FileRepository;
import com.test.testParserIdCard.service.FileService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileServiceImpl implements FileService {
    final FileRepository fileRepository;

    final String url = "C:\\Users\\ulano\\OneDrive\\Рабочий стол\\images";

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File saveFile(MultipartFile file) {
        try {
            java.io.File tempFile = new java.io.File(url);

            File fileInDataBase = fileRepository.save(
                    File.builder()
                            .fileName(file.getOriginalFilename())
                            .fileUrl(tempFile.getAbsolutePath() + "\\")
                            .build()
            );

            file.transferTo(Paths.get(fileInDataBase.getFileUrl() + fileInDataBase.getFileName()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
