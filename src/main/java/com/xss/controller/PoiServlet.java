package com.xss.controller;

import com.xss.service.PoiService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author XSS
 * @date 2020/7/1
 * @desc
 */
@WebServlet("/poi/*")
public class PoiServlet extends BaseServlet{


    public void exportExcel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        username=username==null?"":username;
        String gender = req.getParameter("gender");
        gender=gender==null?"-1":gender;
        String deptId = req.getParameter("deptId");
        deptId=deptId==null?"-1":deptId;

        ServletOutputStream os = resp.getOutputStream();
        resp.setHeader("Content-Disposition", "attachment;filename=" + new String("用户信息.xlsx".getBytes("utf-8"), "iso-8859-1"));
        resp.setContentType("application/ynd.ms-excel;charset=UTF-8");

        XSSFWorkbook wb = PoiService.UserExcel(username,gender,deptId);

        wb.write(os);

        if (os!=null){
            os.close();
        }
    }
}
