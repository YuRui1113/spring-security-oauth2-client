/*
 * File: src\main\java\com\taylor\oauth2\configuration\SecurityConfig.java
 * Project: spring-security-oauth2-api
 * Created Date: Thursday, March 14th 2024, 2:26:28 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Friday, 15th March 2024 7:42:30 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Thursday, March 14th 2024	Rui Yu		Initial version
 */

package com.taylor.oauth2.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private static final String AUTHORITY_PREFIX = "SCOPE_";
        private static final String SCOPE_BOOK = AUTHORITY_PREFIX + "apiBook";
        private static final String SCOPE_BOOK_READ = SCOPE_BOOK + ".read";
        private static final String SCOPE_BOOK_WRITE = SCOPE_BOOK + ".write";

        @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
        private String issuerUri;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(HttpMethod.GET, "/api/v1/book/**")
                                                .hasAnyAuthority(SCOPE_BOOK_READ, SCOPE_BOOK_WRITE)
                                                .requestMatchers(HttpMethod.POST, "/api/v1/book/**")
                                                .hasAuthority(SCOPE_BOOK_WRITE)
                                                .requestMatchers(HttpMethod.PUT, "/api/v1/book/**")
                                                .hasAuthority(SCOPE_BOOK_WRITE)
                                                .requestMatchers(HttpMethod.DELETE, "/api/v1/book/**")
                                                .hasAuthority(SCOPE_BOOK_WRITE)
                                                .anyRequest().authenticated())
                                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

                return http.build();
        }

        @Bean
        public JwtDecoder jwtDecoder() {
                // By default, Spring don't allow JWT header type 'at+jwt', need to manually
                // allow like below
                DefaultJOSEObjectTypeVerifier<SecurityContext> verifier = new DefaultJOSEObjectTypeVerifier<>(
                                new JOSEObjectType("at+jwt"));

                // NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri)
                NimbusJwtDecoder decoder = NimbusJwtDecoder.withIssuerLocation(this.issuerUri)
                                .jwtProcessorCustomizer((processor) -> processor.setJWSTypeVerifier(verifier))
                                .build();

                return decoder;
        }
}