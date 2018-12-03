package com.shokey.brushadmin.server;

import com.shokey.brushdao.hibernate.UserDao;
import com.shokey.brushentity.User;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailServer implements UserDetailsService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (StringUtils.isBlank(s))
            throw new UsernameNotFoundException("用户名不能为空");
        User user = userDao.findByAndMobile(s);
        if (user == null)
            throw new UsernameNotFoundException("未注册的用户");
        if (user.getStatus() == -1)
            throw new UsernameNotFoundException("用户被锁定");
        //获取用户角色
        List<GrantedAuthority> list=new ArrayList<>();
        return null;
    }
}
