package com.wyl.test.dao;

import com.wyl.test.entity.User;
import com.wyl.test.util.Page;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 11:59
 * @Description:
 */
public interface IUserDao {

    User getById(Long id);

    Long add(User user);

    void updateUser(User user);

    void delete(Long id);

    Page<User> page(Page page);
}
