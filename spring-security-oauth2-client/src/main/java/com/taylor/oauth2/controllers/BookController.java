/*
 * File: src\main\java\com\taylor\oauth2\controllers\BookController.java
 * Project: spring-security-oauth2-client
 * Created Date: Monday, March 18th 2024, 4:47:28 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Friday, 22nd March 2024 8:02:12 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Monday, March 18th 2024	Rui Yu		Initial version
 */

package com.taylor.oauth2.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taylor.oauth2.models.Book;
import com.taylor.oauth2.models.PaginatedItemsView;
import com.taylor.oauth2.services.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    @Value("${restApi}")
    private String restApi;

    public BookController(BookService bookService, OAuth2AuthorizedClientService authorizedClientService) {
        this.bookService = bookService;
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/list")
    public String bookList(Model model, @RequestParam("page") Optional<Integer> page, Authentication authentication) {
        PaginatedItemsView<Book> paginatedBooks = this.bookService.getBooks(
                page.isPresent() ? page.get() : 0);
        model.addAttribute("books", paginatedBooks);

        OAuth2AuthorizedClient authorizedClient = this.authorizedClientService.loadAuthorizedClient("springMvcClient",
                authentication.getName());
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        model.addAttribute("accessToken", accessToken.getTokenValue());
        model.addAttribute("restApi", restApi);

        return "book/list";
    }

    @GetMapping("/addNewBook")
    public String addNewBook(Model model) {
        model.addAttribute("book", new Book());
        return "book/details";
    }

    @GetMapping("/editBook")
    public String editBook(Model model, Book book) {
        model.addAttribute("book", book);
        return "book/details";
    }
}