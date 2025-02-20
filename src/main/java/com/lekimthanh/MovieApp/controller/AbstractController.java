package com.lekimthanh.MovieApp.controller;

import com.lekimthanh.MovieApp.model.User;

import jakarta.servlet.http.HttpServletRequest;

public abstract class AbstractController {

    static final String SESSION_ATTRIBUTE_USER = "user";

    User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(SESSION_ATTRIBUTE_USER);
    }

}
