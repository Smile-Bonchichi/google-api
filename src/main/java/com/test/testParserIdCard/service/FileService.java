package com.test.testParserIdCard.service;

import com.test.testParserIdCard.entity.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    File saveFile(MultipartFile file);
}
