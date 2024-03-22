/*
 * File: src\main\java\com\taylor\oauth2\models\Book.java
 * Project: spring-security-oauth2-client
 * Created Date: Tuesday, March 19th 2024, 4:02:27 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 21st March 2024 8:06:34 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Tuesday, March 19th 2024	Rui Yu		Initial version
 */

package com.taylor.oauth2.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Book {

    private Long id;

    @NotEmpty(message = "Field title is required")
    private String title;

    @NotEmpty(message = "Field author is required")
    private String author;

    @NotNull(message = "Field publicationYear is required")
    private Short publicationYear;

    @NotEmpty(message = "Field ISBN is required")
    private String isbn;
}