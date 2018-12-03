package com.shokey.brushdao.hibernate;

import com.shokey.brushentity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    User findByAndMobile(String phone);
}
