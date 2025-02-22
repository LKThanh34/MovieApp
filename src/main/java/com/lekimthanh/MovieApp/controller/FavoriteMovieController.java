package com.lekimthanh.MovieApp.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lekimthanh.MovieApp.dto.FavoriteMovieRequest;
import com.lekimthanh.MovieApp.dto.FavoriteMovieResponse;
import com.lekimthanh.MovieApp.dto.GetUserFavoriteMovieResponse;
import com.lekimthanh.MovieApp.exception.MovieNotFoundException;
import com.lekimthanh.MovieApp.model.User;
import com.lekimthanh.MovieApp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class FavoriteMovieController extends AbstractController {
    private static final String SESSION_ATTRIBUTE_USER = "user";
    private final UserService userService;

    @GetMapping("/get-user-favorite-movies")
    public ResponseEntity<GetUserFavoriteMovieResponse> get(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTE_USER);
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        log.info(String.format("get favorite movide for user {0}", user.getId()));
        GetUserFavoriteMovieResponse response = userService.getUserFavoriteMovies(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/favorite-movie")
    public ResponseEntity<FavoriteMovieResponse> favoriteMovie(
            @Valid @RequestBody FavoriteMovieRequest favoriteMovieRequest,
            HttpServletRequest request) throws MovieNotFoundException {
        final User user = getUserFromSession(request);
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        log.info(String.format("user {0} favorite movie {1}", user.getId(), favoriteMovieRequest.getMovieid()));
        userService.favoriteMovie(user, favoriteMovieRequest);
        return ResponseEntity.ok().build();
    }
}
