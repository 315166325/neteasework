package com.netease.work.interceptor;

import com.netease.work.Model.HostHolder;
import com.netease.work.pojo.User;
import com.netease.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对所有需要登录身份的页面进行拦截，没有登录则跳至登陆页。
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        System.out.println(httpServletRequest.getRequestURL());测试
        if (httpServletRequest.getCookies()!=null) {// != null
            for (Cookie cookie : httpServletRequest.getCookies()) {
                    if(cookie.getName().equals("user")){
                    User user = new User();
                    user.setUsername(cookie.getValue());
                    user.setId(userService.getIdByName(cookie.getValue()));
                    hostHolder.setUser(user);
                }
            }
        }
      if(hostHolder.getUser() == null){
                httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
                return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
