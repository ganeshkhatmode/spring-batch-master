package com.batch.user_processing.service.impl;

import com.batch.user_processing.UserRepository;
import com.batch.user_processing.model.User;
import com.batch.user_processing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
