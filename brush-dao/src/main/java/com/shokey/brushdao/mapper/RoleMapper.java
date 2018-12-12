package com.shokey.brushdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shokey.brushentity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select t_user_role.role_id from t_user_role where mobile = #{mobile}")
    List<Integer> findByMobile(String mobile);

    @Select("select role_id,role_name,remark,create_time from t_role where role_id = #{id}")
    Role findByRoleId(Integer id);
}
