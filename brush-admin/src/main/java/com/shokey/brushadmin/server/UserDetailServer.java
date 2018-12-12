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
//        UpdateWrapper<Role> uqw = new  UpdateWrapper<>();
//        System.out.println("用户："+s);
//        uqw.setSql("select * from t_role where role_id = (select t_user_role.role_id from t_user_role where mobile = '"+s+"')");
//        System.out.println(uqw.getSqlSelect());
        List<Integer> roleIds = roleMapper.findByMobile(s);
        List<GrantedAuthority> list=new ArrayList<>();
        for (Integer roleId:roleIds) {
            Role role = roleMapper.findByRoleId(roleId);
            System.out.println("拥有的权限"+role.getRoleName());
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),list);
    }
}
