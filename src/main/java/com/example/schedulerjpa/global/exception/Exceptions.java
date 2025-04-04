package com.example.schedulerjpa.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

//성원 튜터님이 제공해주신걸 가져다 썼고, code는 어떻게 쓸지 잘몰라서 뺐습니다.
@AllArgsConstructor
@Getter
public enum Exceptions {

    // Common
    INVALID_INPUT_VALUE(400, "Bad Request", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed","Method Not Allowed"),
    ENTITY_NOT_FOUND(400, "Bad Request", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "Server Error", "Internal Server Error"),
    INVALID_TYPE_VALUE(400, "Bad Request", "Invalid Type Value"),

    // User
    EMAIL_DUPLICATION(400, "Bad Request", "Email is Duplicated"),
    USER_NOT_FOUND(404, "Not Found", "User Not Found"),
    INVALID_PASSWORD(400, "Bad Request", "Invalid Password"),
    UNAUTHORIZED_ACCESS(400, "Bad Request", "Unauthorized Access"),

    // Schedule
    SCHEDULE_NOT_FOUND(404, "Not Found", "Schedule Not Found"),

    // Comment
    COMMENT_NOT_FOUND(404, "Not Found", "Comment Not Found");

    private final int status;
    private final String error;
    private final String message;
}