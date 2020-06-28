package com.xss.controller;

import com.xss.constants.SysConstants;
import com.xss.entity.User;
import com.xss.service.LoginService;
import com.xss.service.UserService;
import com.xss.utils.EmailUtil;
import com.xss.utils.MDUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author XSS
 * @date 2020/6/28
 * @desc
 */
@WebServlet("/forget/*")
public class ForgetServlet extends BaseServlet{

    public void email(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        HttpSession session = req.getSession();

        PrintWriter out = resp.getWriter();
        if (!(email == null || "".equals(email))) {
            //随机验证码
            String randomCode = Math.random() + "";
            randomCode = randomCode.substring(randomCode.length() - 5, randomCode.length() - 1);
            boolean b = EmailUtil.sendEmail(email, randomCode);
            if (b) {
                //把验证码放到session中
                session.setAttribute(SysConstants.SESSION_CODE, randomCode);
                session.setMaxInactiveInterval(60);
                out.write("1");
                return;
            }
        }
        out.write("0");
    }


    public void updatePs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        User userByN = UserService.findUserByN(username);
        PrintWriter pw = resp.getWriter();


        Object o = session.getAttribute(SysConstants.SESSION_CODE);

        if (o!=null){
            if (userByN!=null&&code.equals(o.toString())){
                LoginService.updatePs(MDUtil.md5(password),username);
                resp.sendRedirect("/index.jsp");
            }else {

                req.getRequestDispatcher("/jsp/login/forget.jsp").forward(req,resp);
                /*resp.sendRedirect("/jsp/login/forget.jsp");*/
            }
        }else {

            req.getRequestDispatcher("/jsp/login/forget.jsp").forward(req,resp);
        }
    }
}
