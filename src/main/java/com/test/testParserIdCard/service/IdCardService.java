package com.test.testParserIdCard.service;

import com.test.testParserIdCard.dto.RequestDto;
import com.test.testParserIdCard.dto.ResponseDto;

public interface IdCardService {
    ResponseDto getFrontSide(RequestDto requestDto);

    ResponseDto getBackSide(RequestDto requestDto);
}
