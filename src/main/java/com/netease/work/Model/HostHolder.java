package com.netease.work.Model;

import com.netease.work.pojo.User;
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
