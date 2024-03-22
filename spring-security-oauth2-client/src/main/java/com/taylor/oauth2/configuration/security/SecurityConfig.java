/*
 * File: src\main\java\com\taylor\oauth2\configuration\security\SecurityConfig.java
 * Project: spring-security-oauth2-client
 * Created Date: Monday, March 18th 2024, 4:41:06 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Friday, 22nd March 2024 8:00:34 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Monday, March 18th 2024	Rui Yu		Initial version
 */

package com.taylor.oauth2.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        public static final String[] ENDPOINTS_WHITELIST = {
                        "/css/**",
                        "/js/**",
                        "/font-awesome/**",
                        "/bootstrap-5.2.3-dist/css/**",
                        "/",
                        "/index",
                        "/layout.html"
        };

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(
                                authorize -> authorize
                                                // We are permitting all static resources to be accessed publicly
                                                .requestMatchers(ENDPOINTS_WHITELIST)
                                                .permitAll()
                                                .requestMatchers("/anonymous").anonymous()
                                                .anyRequest()
                                                .authenticated())
                                .oauth2Login(Customizer.withDefaults())
                                .oauth2Client(Customizer.withDefaults())
                                .exceptionHandling(exception -> {
                                        exception.accessDeniedPage("/accessDenied");
                                });

                return http.build();
        }
}