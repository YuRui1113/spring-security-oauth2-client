/*
 * File: src\main\java\com\taylor\oauth2\controllers\BookController.java
 * Project: spring-security-oauth2-api
 * Created Date: Sunday, February 25th 2024, 7:27:24 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Friday, 22nd March 2024 3:30:57 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Sunday, February 25th 2024	Rui Yu		Initial version
 */

package com.taylor.oauth2.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taylor.oauth2.orm.entities.Book;
import com.taylor.oauth2.services.BookService;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // Get books by page
    @GetMapping()
    public Page<Book> getBooksByPage(@RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size) {
        return service.getBooksByPage(page, size);
    }

    // Create a new book
    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = service.createBook(book);
        return ResponseEntity.ok(newBook);
    }

    // Get book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBookById(id));
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = service.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        service.deleteStuduent(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}