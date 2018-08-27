package com.wyl.test.service;

import com.wyl.test.entity.User;
import com.wyl.test.util.Page;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 12:00
 * @Description:
 */
public interface IUserService {

    User getById(Long id);

    User add(User user);

    User update(User user);

    void delete(Long id);

    Page<User> page(Page page);

}
