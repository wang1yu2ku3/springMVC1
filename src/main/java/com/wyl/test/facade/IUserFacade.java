package com.wyl.test.facade;

import com.wyl.test.entity.User;
import com.wyl.test.util.Page;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 12:06
 * @Description:
 */
public interface IUserFacade {

    User getById(Long id);

    User add(User user);

    User update(User user);

    void delete(Long id);

    Page<User> page(Page page);
}
