package com.xss.controller;

import com.alibaba.fastjson.JSONObject;
import com.xss.constants.SysConstants;
import com.xss.entity.User;
import com.xss.service.OtherLoginService;
import com.xss.service.UserService;
import com.xss.utils.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;

/**
 * @author XSS
 * @date 2020/6/30
 * @desc
 */

@WebServlet("/otherLogin/*")
public class OtherLoginServlet extends BaseServlet{

    public void weChatLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties pro = new Properties();
        pro.load(OtherLoginServlet.class.getClassLoader().getResourceAsStream("config.properties"));

        String appid = pro.getProperty("wx.AppID");

        //微信授权成功后的回调地址
        String redirect_uri = pro.getProperty("wx.redirect_uri");

        //Step1：获取Authorization Code
        String url = "https://open.weixin.qq.com/connect/qrconnect?response_type=code"+
                "&appid=" + appid +
                "&redirect_uri=" + URLEncoder.encode(redirect_uri, "GBK") +
                "&scope=snsapi_login";

        /*System.out.println(url);*/
        // 重定向到微信登录指定的地址进行微信登录授权,授权成功后返回code
        resp.sendRedirect(url);

    }

    //授权成功回调
    public void callBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Properties pro = new Properties();
        pro.load(OtherLoginServlet.class.getClassLoader().getResourceAsStream("config.properties"));
        String code = req.getParameter("code");

        String appid = pro.getProperty("wx.AppID");
        String secret = pro.getProperty("wx.AppSecret");

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid +
                "&secret=" + secret +
                "&code=" + code +
                "&grant_type=authorization_code";

        //获取AccessToken、openid等数据
        JSONObject auth = AuthUtil.auth(url);

        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" +
                auth.getString("access_token") +
                "&openid=" + auth.getString("openid");

        JSONObject userInfo = AuthUtil.auth(url);


        User user = OtherLoginService.findByOpenId(userInfo.getString("openid"));


        if (user==null){

            user = new User();

            // 用户的头像
            user.setPic(userInfo.getString("headimgurl"));

            // 性别
            user.setGender(userInfo.getString("sex"));
            // 用户的昵称
            user.setRealName(userInfo.getString("nickname"));

            //设置用户密码
            user.setPassword("123");

            user.setRegisterTime(new Date());

            // 随机用户名
            user.setUsername(String.valueOf(System.currentTimeMillis()));

            user.setWxOpenid(userInfo.getString("openid"));
            // 注册一个新的用户
            UserService.addUser(user);

        }

        session.setAttribute(SysConstants.SESSION_LOGIN_CHECK, user);
        req.getRequestDispatcher("/jsp/common/home.jsp").forward(req, resp);

    }
}
