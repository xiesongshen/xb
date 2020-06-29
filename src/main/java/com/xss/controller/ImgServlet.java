package com.xss.controller;

import com.xss.constants.SysConstants;
import com.xss.entity.User;
import com.xss.service.UserService;
import com.xss.utils.ImgCodeUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @author XSS
 * @date 2020/6/29
 * @desc
 */

@WebServlet("/img/*")
public class ImgServlet extends BaseServlet {

    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImgCodeUtil imgCodeUtil = new ImgCodeUtil();

        HttpSession session = req.getSession();

        BufferedImage image = imgCodeUtil.getImage();
        String text = imgCodeUtil.getText();

        session.setAttribute(SysConstants.SESSION_IMGCODE,text);

        ServletOutputStream os = resp.getOutputStream();

        // 禁止图像缓存
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        ImageIO.write(image,"jpeg",os);

        os.flush();
        if (os!=null){
            os.close();
        }
    }

    public void upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //为解析类提供配置信息 创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置文件最大解析大小(单位：字节)
        sfu.setFileSizeMax(1024 * 1024 * 2);

        PrintWriter pw = resp.getWriter();

        //后缀
        String suffix = "";
        try {
            List<FileItem> items = sfu.parseRequest(req);
            for (FileItem item : items) {
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()){
                    String name = item.getName();
                    String[] split = name.split("\\.");
                    suffix = System.currentTimeMillis()+ "." + split[split.length - 1];

                    String path = SysConstants.FILE_PREFIX+suffix;

                    File file = new File(path);

                    //把文件上传到服务器上
                    if (!file.exists()){
                        //将文件写出到指定磁盘（即保存图片的服务器）
                        item.write(file);
                    }
                }
            }

            User user =(User) req.getSession().getAttribute(SysConstants.SESSION_LOGIN_CHECK);

            UserService.updateHeadImg(suffix,user.getId());
            pw.write("1");

        } catch (Exception e) {
            e.printStackTrace();
            pw.write("0");
        }
    }

    public void getHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User userById = UserService.findUserById(Integer.valueOf(id));
        String path = SysConstants.FILE_PREFIX + userById.getPic();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        ServletOutputStream os = resp.getOutputStream();

        int len;
        byte[] b = new byte[1024*2];
        while ((len=bis.read(b))!=-1){
            os.write(b,0,len);
        }
        os.flush();
        if (os!=null){
            os.close();
        }
        if (bis!=null){
            bis.close();
        }
    }
}
