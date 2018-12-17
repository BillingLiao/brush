package com.shokey.brushadmin.server;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shokey.brushadmin.black.Black2b;
import com.shokey.brushcommon.json.API;
import com.shokey.brushcommon.json.jsonModel;
import com.shokey.brushcommon.tool.GoogleAuthenticator;
import com.shokey.brushdao.mapper.UserMapper;
import com.shokey.brushentity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //激活方法
    public jsonModel Activation(String code){
        //获取登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName() == "anonymousUser"){
            log.info("当前无用户登录!");
            return API.error("当前无登录用户！");
        }
        //查询用户
        LambdaQueryWrapper<User> u = new QueryWrapper<User>().lambda().eq(User::getMobile,authentication.getName());
        User user = userMapper.selectOne(u);
        if (user == null){
            log.info("获取用户信息出错!");
            return API.error("获取用户信息出错！");
        }
        //判断是否需要激活
        if (user.getStatus() != 1){
            log.info("当前用户无需激活操作!");
            return API.error("当前用户无需激活操作!");
        }
        //激活操作
        long t = System.currentTimeMillis();
        try {
        long c = Long.valueOf(code);
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(6);
        if (ga.check_code(user.getSecret(), c, t)){
            user.setStatus(0);
        }
        }catch (Exception e){
            log.info("转换谷歌验证码出错！");
            API.error("转换谷歌验证码出错！");
        }
         return API.success(userMapper.update(user,u));
    }
}
