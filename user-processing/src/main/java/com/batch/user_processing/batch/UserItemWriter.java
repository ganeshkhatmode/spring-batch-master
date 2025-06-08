package com.batch.user_processing.batch;

import com.batch.user_processing.model.User;
import com.batch.user_processing.service.UserService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserItemWriter implements ItemWriter<User> {
    @Autowired
    private UserService userService;

    @Override
    public void write(Chunk<? extends User> chunk) throws Exception {
        for(User user : chunk){
            userService.save(user);
        }
    }
}
