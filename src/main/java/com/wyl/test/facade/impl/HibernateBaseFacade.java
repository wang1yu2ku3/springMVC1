package com.wyl.test.facade.impl;

import com.wyl.test.service.impl.HibernateBaseService;
import com.wyl.test.util.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


/**
 * @fileName       HibernateBaseFacade.java
 * @packageName    com.vianet.sceportal.web.facade.impl
 * @description    所有Hibernate常用的操作,封装所有Facade共有的filed和method
 * @author         Invisiller King
 * @since          1.0
 * @createTime     May 6, 2014 3:32:16 PM
 */
@Service
public class HibernateBaseFacade<T, PK extends Serializable> {

	/*@Autowired
	@Qualifier("hibernateBaseService")
	protected HibernateBaseService<T, PK> hibernateBaseService;
	protected static Logger logger;

	@SuppressWarnings("static-access")
	public HibernateBaseFacade() {
		logger = logger.getLogger(getClass().getName());
	}

	public T get(PK id) {
		return hibernateBaseService.get(id);
	}

	public T load(PK id) {
		return hibernateBaseService.load(id);
	}

	public List<T> getByIds(PK[] ids) {
		return hibernateBaseService.getByIds(ids);
	}

	public T getUniqueBy(String propertyName, Object value) {
		return hibernateBaseService.getUniqueBy(propertyName, value);
	}

	public List<T> getListBy(String propertyName, Object value) {
		return hibernateBaseService.getListBy(propertyName, value);
	}

	public List<T> getAll() {
		return hibernateBaseService.getAll();
	}

	public List<T> getAllOrderBy(String propertyName) {
		return hibernateBaseService.getAllOrderBy(propertyName);
	}

	public List<T> getAllOrderBy(String propertyName, boolean isDesc) {
		return hibernateBaseService.getAllOrderBy(propertyName, isDesc);
	}

	public Long getTotalCount() {
		return hibernateBaseService.getTotalCount();
	}

	public boolean isPropertyUnique(String propertyName, Object oldValue, Object newValue) {
		return hibernateBaseService.isPropertyUnique(propertyName, oldValue, newValue);
	}

	public boolean isExisting(String propertyName, Object value) {
		return hibernateBaseService.isExisting(propertyName, value);
	}

	public PK save(T entity) {
		return hibernateBaseService.save(entity);
	}

	public void saveOrUpdate(T entity) {
		hibernateBaseService.saveOrUpdate(entity);
	}

	public void update(T entity) {
		hibernateBaseService.update(entity);
	}

	public void delete(T entity) {
		hibernateBaseService.delete(entity);
	}

	public void delete(PK id) {
		hibernateBaseService.delete(id);
	}

	public void deleteByIds(PK[] ids) {
		hibernateBaseService.deleteByIds(ids);
	}

	public void deleteByIds(List<PK> ids) {
		hibernateBaseService.deleteByIds(ids);
	}

	public void flush() {
		hibernateBaseService.flush();
	}

	public void clear() {
		hibernateBaseService.clear();
	}

	public void evict(Object object) {
		hibernateBaseService.evict(object);
	}

	public void initEntity(T entity) {
		hibernateBaseService.initEntity(entity);
	}

	public void initEntity(List<T> entityList) {
		hibernateBaseService.initEntity(entityList);
	}

	public String getIdName() {
		return hibernateBaseService.getIdName();
	}

	public T findUniqueBy(String propertyName, Object value) {
		return hibernateBaseService.findUniqueBy(propertyName, value);
	}

	public List<T> findBy(String propertyName, Object value) {
		return hibernateBaseService.findBy(propertyName, value);
	}

	public List<T> findByIds(List<PK> ids) {
		return hibernateBaseService.findByIds(ids);
	}

	// Page Pagination ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Page<T> getAll(Page<T> page) {
		return hibernateBaseService.getAll(page);
	}

	// hibernate Base Facade ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public T get(Class<?> clazz, PK id) {
		return hibernateBaseService.get(clazz, id);
	}

	public T load(Class<?> clazz, PK id) {
		return hibernateBaseService.load(clazz, id);
	}

	public List<T> getByIds(Class<?> clazz, PK[] ids) {
		return hibernateBaseService.getByIds(clazz, ids);
	}

	public T getUniqueBy(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseService.getUniqueBy(clazz, propertyName, value);
	}

	public List<T> getListBy(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseService.getListBy(clazz, propertyName, value);
	}

	public List<T> getAll(Class<?> clazz) {
		return hibernateBaseService.getAll(clazz);
	}

	public List<T> getAllOrderBy(Class<?> clazz, String propertyName) {
		return hibernateBaseService.getAllOrderBy(clazz, propertyName);
	}

	public List<T> getAllOrderBy(Class<?> clazz, String propertyName, boolean isDesc) {
		return hibernateBaseService.getAllOrderBy(clazz, propertyName, isDesc);
	}

	public Long getTotalCount(Class<?> clazz) {
		return hibernateBaseService.getTotalCount(clazz);
	}

	public boolean isPropertyUnique(Class<?> clazz, String propertyName, Object oldValue, Object newValue) {
		return hibernateBaseService.isPropertyUnique(clazz, propertyName, oldValue, newValue);
	}

	public boolean isExisting(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseService.isExisting(clazz, propertyName, value);
	}

	public PK save(Class<?> clazz, T entity) {
		return hibernateBaseService.save(clazz, entity);
	}

	public void saveOrUpdate(Class<?> clazz, T entity) {
		hibernateBaseService.saveOrUpdate(clazz, entity);
	}

	public void update(Class<?> clazz, T entity) {
		hibernateBaseService.update(clazz, entity);
	}

	public void delete(Class<?> clazz, T entity) {
		hibernateBaseService.delete(clazz, entity);
	}

	public void delete(Class<?> clazz, PK id) {
		hibernateBaseService.delete(clazz, id);
	}

	public void deleteByIds(Class<?> clazz, PK[] ids) {
		hibernateBaseService.deleteByIds(clazz, ids);
	}

	public void deleteByIds(Class<?> clazz, List<PK> ids) {
		hibernateBaseService.deleteByIds(clazz, ids);
	}

	public void evict(Class<?> clazz, Object object) {
		hibernateBaseService.evict(clazz, object);
	}

	public void initEntity(Class<?> clazz, T entity) {
		hibernateBaseService.initEntity(clazz, entity);
	}

	public void initEntity(Class<?> clazz, List<T> entityList) {
		hibernateBaseService.initEntity(clazz, entityList);
	}

	public String getIdName(Class<?> clazz) {
		return hibernateBaseService.getIdName(clazz);
	}

	public T findUniqueBy(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseService.findUniqueBy(clazz, propertyName, value);
	}

	public List<T> findBy(Class<?> clazz, String propertyName, Object value) {
		return hibernateBaseService.findBy(clazz, propertyName, value);
	}

	public List<T> findByIds(Class<?> clazz, List<PK> ids) {
		return hibernateBaseService.findByIds(clazz, ids);
	}

	// Page Pagination ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Page<T> getAll(Class<?> clazz, Page<T> page) {
		return hibernateBaseService.getAll(clazz, page);
	}
*/
}
