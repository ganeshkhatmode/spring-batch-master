package com.batch.user_processing.batch;

import com.batch.user_processing.model.User;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class UserItemReader implements ItemReader<User> {

    @Override
    public User read() throws Exception {
        return new User();
    }
}
