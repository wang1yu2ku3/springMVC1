package com.wyl.test.service.impl;

import com.wyl.test.dao.IUserDao;
import com.wyl.test.entity.User;
import com.wyl.test.service.IUserService;
import com.wyl.test.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

import javax.transaction.Transactional;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 12:01
 * @Description:
 */
@Service
public class UserServiceImpl extends HibernateBaseService<User, Long> implements IUserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("userDaoImpl")
    private IUserDao iUserDao;


    @Override
    public User getById(Long id) {
        return iUserDao.getById(id);
    }

    @Override
    public User add(User user) {
        Long id = iUserDao.add(user);
        return iUserDao.getById(id);
    }

    @Override
    public User update(User user) {
        this.iUserDao.updateUser(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        this.iUserDao.delete(id);
    }

    @Override
    public Page<User> page(Page page) {
        return this.iUserDao.page(page);
    }
}
