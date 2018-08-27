package com.wyl.test.service.impl;

import com.wyl.test.dao.impl.HibernateBaseDao;
import com.wyl.test.util.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


/**
 * @fileName       HibernateBaseService.java
 * @description    所有Service的父类,封装所有Service共有的filed和method
 * @author         yulin.wang
 * @since          1.0
 * @createTime     May 5, 2015 3:03:14 PM
 */
@Service
public class HibernateBaseService<T, PK extends Serializable> {

	/*@Autowired
	@Qualifier("hibernateBaseDao")
	protected HibernateBaseDao<T, PK> hibernateBaseDao;
	protected static Logger logger;

	@SuppressWarnings("static-access")
	public HibernateBaseService() {
		logger = logger.getLogger(getClass().getName());
	}

	public T get(PK id) {
		return hibernateBaseDao.get(id);
	}

	public T load(PK id) {
		return hibernateBaseDao.load(id);
	}

	public List<T> getByIds(PK[] ids) {
		return hibernateBaseDao.getByIds(ids);
	}

	public T getUniqueBy(String propertyName, Object value) {
		return hibernateBaseDao.getUniqueBy(propertyName, value);
	}

	public List<T> getListBy(String propertyName, Object value) {
		return hibernateBaseDao.getListBy(propertyName, value);
	}

	public List<T> getAll() {
		return hibernateBaseDao.getAll();
	}

	public List<T> getAllOrderBy(String propertyName) {
		return hibernateBaseDao.getAllOrderBy(propertyName);
	}

	public List<T> getAllOrderBy(String propertyName, boolean isDesc) {
		return hibernateBaseDao.getAllOrderBy(propertyName, isDesc);
	}

	public Long getTotalCount() {
		return hibernateBaseDao.getTotalCount();
	}

	public boolean isPropertyUnique(String propertyName, Object oldValue, Object newValue) {
		return hibernateBaseDao.isPropertyUnique(propertyName, oldValue, newValue);
	}

	public boolean isExisting(String propertyName, Object value) {
		return hibernateBaseDao.isExisting(propertyName, value);
	}

	public PK save(T entity) {
		return hibernateBaseDao.save(entity);
	}

	public void saveOrUpdate(T entity) {
		hibernateBaseDao.saveOrUpdate(entity);
	}

	public void update(T entity) {
		hibernateBaseDao.update(entity);
	}

	public void delete(T entity) {
		hibernateBaseDao.delete(entity);
	}

	public void delete(PK id) {
		hibernateBaseDao.delete(id);
	}

	public void deleteByIds(PK[] ids) {
		hibernateBaseDao.deleteByIds(ids);
	}

	public void deleteByIds(List<PK> ids) {
		hibernateBaseDao.deleteByIds(ids);
	}

	public void flush() {
		hibernateBaseDao.flush();
	}

	public void clear() {
		hibernateBaseDao.clear();
	}

	public void evict(Object object) {
		hibernateBaseDao.evict(object);
	}

	public void initEntity(T entity) {
		hibernateBaseDao.initEntity(entity);
	}

	public void initEntity(List<T> entityList) {
		hibernateBaseDao.initEntity(entityList);
	}

	public String getIdName() {
		return hibernateBaseDao.getIdName();
	}

	public T findUniqueBy(String propertyName, Object value) {
		return hibernateBaseDao.findUniqueBy(propertyName, value);
	}

	public List<T> findBy(String propertyName, Object value) {
		return hibernateBaseDao.findBy(propertyName, value);
	}

	public List<T> findByIds(List<PK> ids) {
		return hibernateBaseDao.findByIds(ids);
	}

	// Page Pagination ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Page<T> getAll(Page<T> page) {
		return hibernateBaseDao.getAll(page);
	}

	// hibernate Base Service ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public T get(Class<?> clazz, PK id) {
		return hibernateBaseDao.get(clazz, id);
	}

	public T load(Class<?> clazz, PK id) {
		return hibernateBaseDao.load(clazz, id);
	}

	public List<T> getByIds(Class<?> clazz, PK[] ids) {
		return hibernateBaseDao.getByIds(clazz, ids);
	}

	public T getUniqueBy(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseDao.getUniqueBy(clazz, propertyName, value);
	}

	public List<T> getListBy(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseDao.getListBy(clazz, propertyName, value);
	}

	public List<T> getAll(Class<?> clazz) {
		return hibernateBaseDao.getAll(clazz);
	}

	public List<T> getAllOrderBy(Class<?> clazz, String propertyName) {
		return hibernateBaseDao.getAllOrderBy(clazz, propertyName);
	}

	public List<T> getAllOrderBy(Class<?> clazz, String propertyName, boolean isDesc) {
		return hibernateBaseDao.getAllOrderBy(clazz, propertyName, isDesc);
	}

	public Long getTotalCount(Class<?> clazz) {
		return hibernateBaseDao.getTotalCount(clazz);
	}

	public boolean isPropertyUnique(Class<?> clazz, String propertyName, Object oldValue, Object newValue) {
		return hibernateBaseDao.isPropertyUnique(clazz, propertyName, oldValue, newValue);
	}

	public boolean isExisting(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseDao.isExisting(clazz, propertyName, value);
	}

	public PK save(Class<?> clazz, T entity) {
		return hibernateBaseDao.save(clazz, entity);
	}

	public void saveOrUpdate(Class<?> clazz, T entity) {
		hibernateBaseDao.saveOrUpdate(clazz, entity);
	}

	public void update(Class<?> clazz, T entity) {
		hibernateBaseDao.update(clazz, entity);
	}

	public void delete(Class<?> clazz, T entity) {
		hibernateBaseDao.delete(clazz, entity);
	}

	public void delete(Class<?> clazz, PK id) {
		hibernateBaseDao.delete(clazz, id);
	}

	public void deleteByIds(Class<?> clazz, PK[] ids) {
		hibernateBaseDao.deleteByIds(clazz, ids);
	}

	public void deleteByIds(Class<?> clazz, List<PK> ids) {
		hibernateBaseDao.deleteByIds(clazz, ids);
	}

	public void evict(Class<?> clazz, Object object) {
		hibernateBaseDao.evict(clazz, object);
	}

	public void initEntity(Class<?> clazz, T entity) {
		hibernateBaseDao.initEntity(clazz, entity);
	}

	public void initEntity(Class<?> clazz, List<T> entityList) {
		hibernateBaseDao.initEntity(clazz, entityList);
	}

	public String getIdName(Class<?> clazz) {
		return hibernateBaseDao.getIdName(clazz);
	}

	public T findUniqueBy(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseDao.findUniqueBy(clazz, propertyName, value);
	}

	public List<T> findBy(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseDao.findBy(clazz, propertyName, value);
	}

	public List<T> findByIds(Class<?> clazz, List<PK> ids) {
		return hibernateBaseDao.findByIds(clazz, ids);
	}

	// Page Pagination ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Page<T> getAll(Class<?> clazz, Page<T> page) {
		return hibernateBaseDao.getAll(clazz, page);
	}
*/
}
