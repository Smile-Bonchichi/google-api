package com.test.testParserIdCard.service.impl;

import com.test.testParserIdCard.dto.UserDto;
import com.test.testParserIdCard.entity.User;
import com.test.testParserIdCard.entity.UserRole;
import com.test.testParserIdCard.repo.RoleRepository;
import com.test.testParserIdCard.repo.UserRepository;
import com.test.testParserIdCard.repo.UserRoleRepository;
import com.test.testParserIdCard.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final UserRoleRepository userRoleRepository;

//    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository
                           ) {//PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
  //      user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsActive(1L);
        userRepository.save(user);

        if (user.getLogin().equalsIgnoreCase("admin")) {
            userRoleRepository.save(UserRole.builder()
                    .user(user)
                    .role(roleRepository.getReferenceById(2L))
                    .build());
        } else {
            userRoleRepository.save(UserRole.builder()
                    .user(user)
                    .role(roleRepository.getReferenceById(1L))
                    .build());
        }

        return user;
    }

    @Override
    public String getToken(UserDto userDto) {
        User user = userRepository.getByLogin(userDto.getLogin());

        if (user == null)
            return "1";

    //    boolean isTrue = passwordEncoder.matches(userDto.getPassword(), user.getPassword());

//        if (isTrue) {
//            return "Basic " + new String(Base64.getEncoder()
//                    .encode((user.getLogin() + ":" + userDto.getPassword()).getBytes()));
//        } else {
            return null;
//        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
