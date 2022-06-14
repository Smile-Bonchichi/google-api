package com.test.testParserIdCard.service;

import com.test.testParserIdCard.dto.UserDto;
import com.test.testParserIdCard.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    String getToken(UserDto userDto);
    List<User> getAll();
}
