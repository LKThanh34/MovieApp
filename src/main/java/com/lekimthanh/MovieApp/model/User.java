package com.lekimthanh.MovieApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class User {
    private int id;
    private String email;
    private String password;
}
