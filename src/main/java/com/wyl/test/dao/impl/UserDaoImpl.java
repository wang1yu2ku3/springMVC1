package com.wyl.test.dao.impl;

import com.wyl.test.dao.IUserDao;
import com.wyl.test.entity.User;
import com.wyl.test.util.Page;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 11:59
 * @Description:
 */
@Repository
@Transactional
public class UserDaoImpl extends HibernateBaseDao<User, Long> implements IUserDao {

    @Override
    public User getById(Long id) {
        return (User) super.getCurrentSession().get(User.class, id);
    }

    @Override
    public Long add(User user) {
        return (Long) super.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        super.getCurrentSession().update(user);
    }

    @Override
    public void delete(Long id) {
        User user = this.get(id);
        if(Objects.isNull(user)) {
            return;
        }
        super.getCurrentSession().delete(user);
    }

    @Override
    public Page<User> page(Page page) {
        StringBuilder hql=new StringBuilder(" from User user where 1=1 ");
        Map<String, String> params= page.getParams();
        if(Objects.nonNull(params) && params.containsKey("gender")) {
            hql.append(" and user.gender =:gender ");
        }
        Page<User> result=super.findPage(page, hql.toString(), params);
        return result;
    }

}
