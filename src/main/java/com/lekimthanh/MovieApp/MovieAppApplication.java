package com.lekimthanh.MovieApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.lekimthanh.MovieApp.dto.FavoriteMovieRequest;
import com.lekimthanh.MovieApp.dto.GetUserFavoriteMovieResponse;
import com.lekimthanh.MovieApp.dto.LoginRequest;
import com.lekimthanh.MovieApp.dto.RegistrationRequest;
import com.lekimthanh.MovieApp.exception.MovieNotFoundException;
import com.lekimthanh.MovieApp.model.User;
import com.lekimthanh.MovieApp.repository.AppRepository;
import com.lekimthanh.MovieApp.service.UserService;

@SpringBootApplication
public class MovieAppApplication {

	public static void main(String[] args) throws MovieNotFoundException {
		ApplicationContext context = SpringApplication.run(MovieAppApplication.class, args);

		String email = "lekimthanhx34@mail.com";
		String password = "password";
		// get created bean
		UserService userService = context.getBean(UserService.class);

		AppRepository appRepository = context.getBean(AppRepository.class);
		// use bean to call functions
		// init db
		appRepository.addMovie(1, "Spider man");
		appRepository.addMovie(2, "Superman");
		appRepository.addMovie(3, "Wonder woman");
		// call service
		userService
				.createUser(RegistrationRequest.builder().email(email).password(password).build());
		User user = userService.findUser(LoginRequest.builder().email(email).password(password).build());
		userService.favoriteMovie(user, FavoriteMovieRequest.builder().movieid(1).build());
		userService.favoriteMovie(user, FavoriteMovieRequest.builder().movieid(2).build());
		GetUserFavoriteMovieResponse favoriteResponse = userService.getUserFavoriteMovies(user);
		System.out.println("favoriteResponse=" + favoriteResponse);
	}

}
