/*
 * File: c:\Work\Docs\spring-security-oauth2-client\spring-security-oauth2-client\src\main\java\com\taylor\oauth2\models\PaginatedItemsView.java
 * Project: jpa
 * Created Date: Tuesday, March 19th 2024, 4:13:21 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Tuesday, 19th March 2024 4:23:14 pm
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

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginatedItemsView<T> {

    private T[] content;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private boolean last;
    private boolean first;
    private int numberOfElements;
    private boolean empty;
}