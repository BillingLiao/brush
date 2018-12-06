package com.shokey.brushadmin.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shokey.brushadmin.black.Black2b;
import com.shokey.brushdao.mapper.UserMapper;
import com.shokey.brushentity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserServer {

    /** logger */
    private static final Logger log = LoggerFactory.getLogger(UserServer.class);

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackOn = Exception.class)
    public boolean Signup(String phone, String password) throws Exception {
        User user = new User();
//        user.setUserId(2);//如果为空，则会传过去null，会报类型异常错误，脑壳痛
        user.setMobile(phone);
        String pass = Black2b.encode(64,password);
        user.setPassword(pass);
        user.setSalt("Bright moonlight in front of bed, suspected frost on the ground.");
        user.setStatus(0);
        user.setRegistrationTime(new Date());
        if (userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getMobile,phone)) != null)
            throw new Exception("已经注册过了哦");
        //写入用户
        if (userMapper.initialUser(user) != 0){
            log.info("写入用户成功");
            //授权用户
            if (userMapper.initialRole(phone) != 0){
                log.info("授权成功");
                return true;
            }else {
                throw new Exception("权限写入失败");
            }
        }else {
            return false;
        }
    }
}
