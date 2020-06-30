package com.xss.service;

import com.xss.dao.OtherLoginDao;
import com.xss.entity.User;

/**
 * @author XSS
 * @date 2020/6/30
 * @desc
 */
public class OtherLoginService {
    private static OtherLoginDao otherLoginDao = new OtherLoginDao();

    public static User findByOpenId(String openid){
        try {
            return otherLoginDao.findByOpenId(openid);
        } catch (Exception e) {
            return null;
        }
    }
}
