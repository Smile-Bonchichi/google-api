package com.test.testParserIdCard.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import javax.sql.DataSource;

////@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    final DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT t.login, t.password, t.is_active FROM users t WHERE t.login = ?")
//                .authoritiesByUsernameQuery(
//                        "SELECT u.login, r.name " +
//                                "FROM users_roles ur " +
//                                "INNER JOIN users u " +
//                                "   on ur.user_id = u.id " +
//                                "INNER JOIN roles r " +
//                                "   on ur.role_id = r.id " +
//                                "WHERE u.login = ? AND u.is_active = 1"
//                );
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .csrf().disable()
//                .authorizeRequests()
//
//                .anyRequest().permitAll()
//
//                .and()
//                .httpBasic();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
