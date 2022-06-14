package com.test.testParserIdCard.controller;

import com.test.testParserIdCard.dto.RequestDto;
import com.test.testParserIdCard.dto.ResponseDto;
import com.test.testParserIdCard.service.IdCardService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {
    final IdCardService idCardService;

    @Autowired
    public MainController(IdCardService idCardService) {
        this.idCardService = idCardService;
    }

    @PostMapping("front")
    public ResponseEntity<ResponseDto> getFrontSide(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(
                idCardService.getFrontSide(
                        RequestDto.builder()
                                .image(image)
                                .build()
                )
        );
    }
}
