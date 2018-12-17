package com.shokey.brushadmin.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shokey.brushcommon.exception.MyUsernameNotFoundException;
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

import java.util.ArrayList;
import java.util.List;

public class UserDetailServer implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws MyUsernameNotFoundException {
        if (StringUtils.isBlank(s))
            throw new MyUsernameNotFoundException("用户名不能为空");
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("mobile",s));
        if (user == null)
            throw new MyUsernameNotFoundException("用户不存在");
        //查询权限
        List<GrantedAuthority> list=new ArrayList<>();
        //看来判断用户激活只有在这搞了
        if (user.getStatus() == 1){
            //设置未激活权限activation
            list.add(new SimpleGrantedAuthority("ROLE_ACTIVATION"));//未激活权限
            return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),list);
        }else {
            List<Integer> roleIds = roleMapper.findByMobile(s);
            for (Integer roleId:roleIds) {
                Role role = roleMapper.findByRoleId(roleId);
                System.out.println("拥有的权限"+role.getRoleName());
                list.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),true,true,true,true,list);
        }
    }
}
