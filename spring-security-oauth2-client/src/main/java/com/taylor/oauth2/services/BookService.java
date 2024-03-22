/*
 * File: src\main\java\com\taylor\oauth2\services\BookService.java
 * Project: spring-security-oauth2-client
 * Created Date: Tuesday, March 19th 2024, 4:05:43 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Wednesday, 20th March 2024 8:00:06 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Tuesday, March 19th 2024	Rui Yu		Initial version
 */

package com.taylor.oauth2.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.taylor.oauth2.models.Book;
import com.taylor.oauth2.models.PaginatedItemsView;

@Service
public class BookService {

    private final WebClient webClient;

    @Value("${pageSize}")
    private int pageSize;

    public BookService(WebClient webClient) {
        this.webClient = webClient;
    }

    public PaginatedItemsView<Book> getBooks(int pageIndex) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/book")
                        .queryParam("page", pageIndex)
                        .queryParam("size", pageSize)
                        .build())
                .retrieve()
                // Can't use PageImpl<Book> because it has no default constructor
                .bodyToMono(new ParameterizedTypeReference<PaginatedItemsView<Book>>() {
                })
                .block();
    }
}