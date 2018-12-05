package com.shokey.brushadmin.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shokey.brushdao.mapper.RoleMapper;
import com.shokey.brushdao.mapper.UserMapper;
import com.shokey.brushentity.Role;
import com.shokey.brushentity.User;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailServer implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (StringUtils.isBlank(s))
            throw new UsernameNotFoundException("用户名不能为空");
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("mobile",s));
        if (user == null)
            throw new UsernameNotFoundException("用户不存在");
        //查询权限
        QueryWrapper<Role> rqw = new  QueryWrapper<>();
        rqw.exists("select role_id from t_user_role where user_id = exists(select t_user.user_id from t_user where mobile = \""+s+"\")");
        List<Role> roles = roleMapper.selectList(rqw);
        List<GrantedAuthority> list=new ArrayList<>();
        for (Role role:roles) {
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),list);
    }
}
