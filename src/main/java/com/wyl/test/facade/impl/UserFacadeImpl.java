package com.wyl.test.facade.impl;

import com.wyl.test.entity.User;
import com.wyl.test.facade.IUserFacade;
import com.wyl.test.service.IUserService;
import com.wyl.test.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 12:06
 * @Description:
 */
@Service(value = "userFacadeImpl")
public class UserFacadeImpl extends HibernateBaseFacade<User, Long> implements
        IUserFacade {

    @Autowired
    @Qualifier("userServiceImpl")
    private IUserService iUserService;


    @Override
    public User getById(Long id) {
        return this.iUserService.getById(id);
    }

    @Override
    public User add(User user) {
        return this.iUserService.add(user);
    }

    @Override
    public User update(User user) {
        return this.iUserService.update(user);
    }

    @Override
    public void delete(Long id) {
        this.iUserService.delete(id);
    }

    @Override
    public Page<User> page(Page page) {
        return this.iUserService.page(page);
    }
}
