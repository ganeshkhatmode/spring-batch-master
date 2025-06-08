package com.batch.user_processing.batch;

import com.batch.user_processing.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User item) throws Exception {
        item.setName(item.getName().toUpperCase());
        item.setCity(item.getCity().toUpperCase());
        return item;
    }
}
