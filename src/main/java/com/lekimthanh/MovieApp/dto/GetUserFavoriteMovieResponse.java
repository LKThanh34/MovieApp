package com.lekimthanh.MovieApp.dto;

import java.util.List;

import com.lekimthanh.MovieApp.model.Movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUserFavoriteMovieResponse {
    List<Movie> favoriteMovies;
}
