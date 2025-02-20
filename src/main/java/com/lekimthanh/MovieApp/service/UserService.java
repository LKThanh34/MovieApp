package com.lekimthanh.MovieApp.service;

import com.lekimthanh.MovieApp.dto.FavoriteMovieRequest;
import com.lekimthanh.MovieApp.dto.GetUserFavoriteMovieResponse;
import com.lekimthanh.MovieApp.dto.LoginRequest;
import com.lekimthanh.MovieApp.dto.RegistrationRequest;
import com.lekimthanh.MovieApp.dto.RegistrationResponse;
import com.lekimthanh.MovieApp.exception.MovieNotFoundException;
import com.lekimthanh.MovieApp.model.User;

public interface UserService {
  
  RegistrationResponse createUser(RegistrationRequest registrationRequest);

  User findUser(LoginRequest loginRequest);

  void favoriteMovie(User user, FavoriteMovieRequest favoriteMovieRequest) throws MovieNotFoundException;

  GetUserFavoriteMovieResponse getUserFavoriteMovies(User user);
}
