package com.test.testParserIdCard.controller;

import com.test.testParserIdCard.dto.UserDto;
import com.test.testParserIdCard.entity.User;
import com.test.testParserIdCard.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation("Создание нового пользователя")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.getToken(userDto));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAll());
    }
}
