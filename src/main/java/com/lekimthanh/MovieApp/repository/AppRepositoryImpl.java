package com.lekimthanh.MovieApp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;

import com.lekimthanh.MovieApp.model.Movie;
import com.lekimthanh.MovieApp.model.User;
import com.lekimthanh.MovieApp.model.UserFavoriteMovie;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AppRepositoryImpl implements AppRepository {

    // lưu id người dùng, tự dộng tăng khi tạo người dùng mới
    private static AtomicInteger atomicUserId = new AtomicInteger();

    // Lưu danh sách tất cả người dùng, với email là key và User là value
    private static Map<String, User> allUsers = new ConcurrentHashMap<>();

    // Lưu danh sách phim, với movield là key và Movie là value
    private static Map<Integer, Movie> allMovies = new ConcurrentHashMap<>();

    // Lưu danh sách phim yêu thích của người dùng dưới dạng cặp (userId, movieId)
    private static Map<UserFavoriteMovie, Integer> allUserFavoriteMovieMap = new ConcurrentHashMap<>();

    // Tạo ngời dùng với email và mật khẩu

    @Override
    public User createNewUser(String email, String password) {
        int id = atomicUserId.getAndIncrement();
        // atomicInterger là một kiểu dữ liệu giúp tăng giá trị mọt cách an toàn trong
        // môi trường đa luồng,
        // getAddIncrement() lấy giá trị hiện tại của atomicUserid, sau đó tăng lên 1.
        // giá trị này dùng làm id cho người dùng mới

        User newUser = User.builder().id(id).email(email).password(password).build();
        // tạo một đối tượng User mới bằng cách sử dụng Builder Pattern
        // gán id, email và password cho người dùng mới.

        User previousUser = allUsers.putIfAbsent(email, newUser);
        if (Objects.isNull(previousUser)) {
            return newUser;
        } else {
            return null;
        }
    }

    @Override
    public User findUserByUserEmail(String email) {
        return allUsers.get(email);
    }

    @Override
    public Movie addMovie(int id, String title) {
        Movie newMovie = Movie.builder().id(id).title(title).build();
        allMovies.put(id, newMovie);
        return newMovie;
    }

    @Override
    public Movie findMovieByMovieId(int movieId) {
        return allMovies.get(movieId);
    }

    @Override
    public void saveFavoriteMovie(int userId, int movieId) {
        allUserFavoriteMovieMap.put(UserFavoriteMovie.builder().userId(userId).movieId(movieId).build(), 1);
        log.info("allUserFavoriteMovieMap" + allUserFavoriteMovieMap);
    }

    @Override
    public List<Integer> getUserFavoritesMovieIds(int userId) {
        List<Integer> movieIds = new ArrayList<>();
        // brute-force
        for (UserFavoriteMovie userFavoriteMovie : allUserFavoriteMovieMap.keySet()) {
            if (userFavoriteMovie.getUserId() == userId) {
                movieIds.add(userFavoriteMovie.getMovieId());
            }
        }
        return movieIds;
    }

}
