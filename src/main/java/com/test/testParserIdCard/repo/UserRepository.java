package com.test.testParserIdCard.repo;

import com.test.testParserIdCard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users t WHERE t.login = :login")
    User getByLogin(@Param("login") String login);
}
