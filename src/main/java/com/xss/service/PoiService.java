package com.xss.service;

import com.xss.dao.PoiDao;
import com.xss.entity.User;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.util.List;

/**
 * @author XSS
 * @date 2020/7/1
 * @desc
 */
public class PoiService {
    private static PoiDao poiDao = new PoiDao();

    public static XSSFWorkbook UserExcel(String username,String gender,String deptId) {
        List<User> user = poiDao.UserExcel(username,gender,deptId);

        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet sheet = wb.createSheet("工作簿");
        XSSFRow r1 = sheet.createRow(0);

        String[] sHead = {"用户名", "部门", "真实姓名", "性别", "邮箱", "电话"};
        for (int i = 0; i < sHead.length; i++) {
            r1.createCell(i).setCellValue(sHead[i]);
        }

        for (int i = 1; i <= user.size(); i++) {
            User u = user.get(i - 1);
            if ("0".equals(u.getGender())) {
                u.setGender("女");
            } else if ("1".equals(u.getGender())) {
                u.setGender("男");
            }else {
                u.setGender("");
            }
            XSSFRow r = sheet.createRow(i);
            r.createCell(0).setCellValue(u.getUsername() == null ? "" : u.getUsername());
            r.createCell(1).setCellValue(u.getDeptName() == null ? "" : u.getDeptName());
            r.createCell(2).setCellValue(u.getRealName() == null ? "" : u.getRealName());
            r.createCell(3).setCellValue(u.getGender());
            r.createCell(4).setCellValue(u.getEmail() == null ? "" : u.getEmail());
            r.createCell(5).setCellValue(u.getPhone() == null ? "" : u.getPhone());
        }

        return wb;
    }
}
