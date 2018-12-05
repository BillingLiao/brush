package com.shokey.brushdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shokey.brushentity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("INSERT INTO `db_brush`.`t_user_role` (`role_id`, `user_id`) VALUES (2, #{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int initialRole(String phone);
}
