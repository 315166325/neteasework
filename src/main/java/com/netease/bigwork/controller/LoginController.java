package com.netease.bigwork.controller;

import com.netease.bigwork.Model.HostHolder;
import com.netease.bigwork.pojo.Goods;
import com.netease.bigwork.pojo.Result;
import com.netease.bigwork.pojo.User;
import com.netease.bigwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 登陆相关的控制
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    HostHolder hostHolder;

    @RequestMapping("/")
    String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("showType", "yes");
        List<Goods> list = userService.getAllGoods();
        model.addAttribute("list", list);
        return "index2";
    }

    @RequestMapping("/login")
    String login11() {
        //直接进入登陆页面
        return "login";
    }

    @ResponseBody
    @RequestMapping("/userLogin")
    public Result userLogin(User user, HttpServletResponse response, Model model) throws IOException {
        Result result = new Result();
        result.setSuccess("success");
        if (userService.check(user)) {
            Cookie cookie = new Cookie("user", user.getUsername());
            cookie.setPath("/");
            cookie.setMaxAge(3600 * 24);
            response.addCookie(cookie);
            result.setCode(200);
            result.setMessage("success");
        } else {
            result.setCode(201);
            result.setMessage("error");
        }
        return result;
    }

    @RequestMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {//hostHolder.getUser().getUsername()
                    Cookie cookie1 = new Cookie("user", null);
                    cookie1.setMaxAge(0);
                    cookie1.setPath("/");
                    Cookie cookie2 = new Cookie("JSESSIONID", null);
                    cookie2.setMaxAge(0);
                    cookie2.setPath("/");
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                    hostHolder.clear();
                }
            }
        }
        try {
            response.sendRedirect("login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
