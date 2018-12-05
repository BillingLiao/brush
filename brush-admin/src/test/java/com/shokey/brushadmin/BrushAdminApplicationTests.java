package com.shokey.brushadmin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shokey.brushdao.mapper.RoleMapper;
import com.shokey.brushdao.mapper.UserMapper;
import com.shokey.brushentity.Role;
import com.shokey.brushentity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.shokey.brushdao.mapper")
public class BrushAdminApplicationTests {

    /** logger */
    private static final Logger log = LoggerFactory.getLogger(BrushAdminApplicationTests.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        User user = new User();
        user.setMobile("123456789");
        User userList = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getMobile,"12345678"));
        Assert.assertEquals("12345678", userList.getMobile());
//        Role role = new Role();
        QueryWrapper<Role> qw = new QueryWrapper<>();

        log.info(""+userList.getPassword());
    }

    @Test
    public void Role(){
        String s = "select role_id,role_name,remark,create_time from t_role where role_id = exists(select role_id from t_user_role where user_id = exists(select t_user.user_id from t_user where mobile = \"12345678\"))";
        QueryWrapper<Role> ew = new QueryWrapper<>();
        ew.exists("select role_id from t_user_role").exists("select t_user.user_id from t_user");
        Role role = roleMapper.selectOne(ew);
        Assert.assertEquals(""+1,""+role.getRoleId());
        log.info("嘿嘿嘿"+ew.getSqlSegment());
    }

}
