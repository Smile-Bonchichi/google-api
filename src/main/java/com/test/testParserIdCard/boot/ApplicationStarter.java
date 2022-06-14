package com.test.testParserIdCard.boot;

import com.test.testParserIdCard.entity.Role;
import com.test.testParserIdCard.repo.RoleRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationStarter implements CommandLineRunner {
    final RoleRepository roleRepository;

    @Autowired
    public ApplicationStarter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(Role.builder()
                .name("ROLE_USER")
                .build());

        roleRepository.save(Role.builder()
                .name("ROLE_ADMIN")
                .build());
    }
}
