package com.batch.user_processing.service;

import com.batch.user_processing.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
