package com.baizhi.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class CaptchaController {

    /**
     * 当前浏览器请求这个方法时，我们会生成验证码图片，并且响应给浏览器
     */
    @RequestMapping("captchaCode")
    public void out(HttpSession session, HttpServletResponse response) throws IOException {
        //1.生成验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 30, 4, 10);

        //2.将验证码存储到session
        session.setAttribute("code", lineCaptcha.getCode());

        //3.将验证码图片响应给浏览器
        OutputStream os = response.getOutputStream();
        lineCaptcha.write(os);

    }

}
