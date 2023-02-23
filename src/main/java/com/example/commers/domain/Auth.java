package com.example.commers.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public class Auth {

    @Data
    public static class SignIn {
        private String email;
        private String password;
    }

    @Data
    public static class SignUp {

        private String nickname;

        private String email;

        private String password;

        private String phone;

        private LocalDate birth;
        private List<String> roles;

    }
}