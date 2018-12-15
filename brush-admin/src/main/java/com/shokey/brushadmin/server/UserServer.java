package com.shokey.brushadmin.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shokey.brushadmin.black.Black2b;
import com.shokey.brushcommon.tool.GoogleAuthenticator;
import com.shokey.brushdao.mapper.UserMapper;
import com.shokey.brushentity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserServer {

    /** logger */
    private static final Logger log = LoggerFactory.getLogger(UserServer.class);

    @Autowired
    private UserMapper userMapper;

    //注册
    @RequestMapping("/tikesignup")
    @Transactional(rollbackOn = Exception.class)
    public boolean Signup(String phone, String password) throws Exception {
        User user = new User();
        user.setMobile(phone);
        String pass = Black2b.encode(64,password);
        user.setPassword(pass);
        user.setSalt("Bright moonlight in front of bed, suspected frost on the ground.");//目前是固定的
        user.setSecret(GoogleAuthenticator.generateSecretKey());//设置谷歌验证随机码
        user.setStatus(0);//所有注册用户均是未激活用户，需要通过绑定谷歌验证器激活，为了开发方便，暂时设置为1
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

    public void Activation(String phone){

    }
}
