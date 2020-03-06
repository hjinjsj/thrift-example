package com.didi.example.event;

import com.didi.example.model.User;
import lombok.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author huangjin
 */
public class CreateUserEvent extends ApplicationContextEvent {
    private User user;

    public CreateUserEvent(ApplicationContext source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
