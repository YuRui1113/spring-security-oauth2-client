/*
 * src\main\java\com\taylor\oauth2\exceptions\ResourceAlreadyExistsException.java
 * Project: spring-security-oauth2-api
 * Created Date: Sunday, February 25th 2024, 6:50:20 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 14th March 2024 9:19:01 am
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Sunday, February 25th 2024	Rui Yu		Initial version
 */

package com.taylor.oauth2.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException() {
        super();
    }

    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}