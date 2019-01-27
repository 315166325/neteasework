package com.netease.bigwork.Model;

import com.netease.bigwork.pojo.User;
import org.springframework.stereotype.Component;

/**
 * 用户做身份拦截
 */

@Component
public class HostHolder {
    private  static ThreadLocal<User> users = new ThreadLocal<>();

    public User getUser() {
        return users.get();
    }

    public static void setUser(User user) {
        users.set(user);
    }
    public void clear(){
        users.remove();
    }
}
