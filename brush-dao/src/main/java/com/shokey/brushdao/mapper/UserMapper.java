package com.shokey.brushdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shokey.brushentity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("INSERT INTO `db_brush`.`t_user_role` (`role_id`, `mobile`) VALUES (2, #{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int initialRole(String phone);

    //还是要自己重写插入方法
    @Insert("INSERT INTO `db_brush`.`t_user` (`user_name`, `password`, `security_password`, `salt`, `mobile`, `qq`, `status`, `registration_time`) VALUES ('NULL', #{password}, 'NULL', #{salt}, #{mobile}, 'NULL', #{status}, #{registrationTime})")
    @Options(useGeneratedKeys = true, keyProperty = "user_id")
    int initialUser(User user);
}
