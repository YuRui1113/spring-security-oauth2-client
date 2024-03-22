/*
 * File: src\main\java\com\taylor\oauth2\configuration\web\WebClientConfig.java
 * Project: spring-security-oauth2-client
 * Created Date: Tuesday, March 19th 2024, 3:58:15 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Friday, 22nd March 2024 7:05:43 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Tuesday, March 19th 2024	Rui Yu		Initial version
 */

package com.taylor.oauth2.configuration.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class WebClientConfig {

    @Value("${restApi}")
    private String restApi;

    @Bean
    public WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction filter = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                authorizedClientManager);
        // filter.setDefaultClientRegistrationId("springMvcClient");
        filter.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder()
                .baseUrl(restApi)
                .apply(filter.oauth2Configuration())
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(logRequest());
                    exchangeFilterFunctions.add(logResponse());
                })
                .build();
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            if (log.isInfoEnabled()) {
                StringBuilder sb = new StringBuilder("Request: \n")
                        .append(clientRequest.method())
                        .append(" ")
                        .append(clientRequest.url());
                clientRequest
                        .headers()
                        .forEach((name, values) -> values.forEach(value -> sb
                                .append("\n")
                                .append(name)
                                .append(":")
                                .append(value)));
                log.info(sb.toString());
            }
            return Mono.just(clientRequest);
        });
    }

    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (log.isInfoEnabled()) {
                StringBuilder sb = new StringBuilder("Response: \n")
                        .append("Status: ")
                        .append(clientResponse.statusCode());
                clientResponse
                        .headers()
                        .asHttpHeaders()
                        .forEach((key, value1) -> value1.forEach(value -> sb
                                .append("\n")
                                .append(key)
                                .append(":")
                                .append(value)));
                log.info(sb.toString());
            }
            return Mono.just(clientResponse);
        });
    }
}