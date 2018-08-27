package com.wyl.test.dao.impl;

import com.wyl.test.util.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @fileName       HibernateBaseDao.java
 * @packageName    com.wyl.test.dao.impl
 * @description    所有Dao的父类,封装所有Dao共有的filed和method
 * @author         RonWang
 * @since          1.0
 * @createTime     May 6, 2014 2:46:42 PM
 * @param <T>      DAO操作的对象类型            
 * @param <PK>     主键类型   
 */
@Repository
public class HibernateBaseDao<T, PK extends Serializable> {

	protected Class<T> entityClass;
	// Spring的applicationContext-dao.xml配置了<bean id="sessionFactory" class="">
	@Autowired
	protected SessionFactory sessionFactory;// 子类可直接使用sessionFactory
	protected static Logger logger;

	@SuppressWarnings({ "unchecked", "static-access" })
	public HibernateBaseDao() {// 通过范型反射，取得子类中定义的entityClass.
		logger = logger.getLogger(getClass().getName());
		this.entityClass = null;
		Type type = this.getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	// BaseDao impl ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getCurrentSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getCurrentSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getByIds(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
		return getCurrentSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	public T getUniqueBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = :" + propertyName;
		return (T) getCurrentSession().createQuery(hql).setParameter(propertyName, value).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListByPrefix(String propertyName, String value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " like " +  ":" + propertyName + "";
		return getCurrentSession().createQuery(hql).setParameter(propertyName, value+"%").list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getListBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return getCurrentSession().createQuery(hql).setParameter(0, value).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String hql = "from " + entityClass.getName();
		return getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllOrderBy(String propertyName) {
		String hql = "from " + entityClass.getName() + " order by ? asc";
		return getCurrentSession().createQuery(hql).setParameter(0, propertyName).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllOrderBy(String propertyName, boolean isDesc) {
		if (!isDesc) {// 如果是顺序
			return getAllOrderBy(propertyName);
		}
		else {
			String hql = "from " + entityClass.getName() + " order by ? desc";
			return getCurrentSession().createQuery(hql).setParameter(0, propertyName).list();
		}
	}

	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getCurrentSession().createQuery(hql).uniqueResult();
	}

	public boolean isPropertyUnique(String propertyName, Object oldValue, Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
				return true;
			}
		}
		return (getUniqueBy(propertyName, newValue) == null);
	}

	public boolean isExisting(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		return (getUniqueBy(propertyName, value) != null);
	}

	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		return (PK) getCurrentSession().save(entity);
	}

	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity is required");
		getCurrentSession().saveOrUpdate(entity);
	}

	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		getCurrentSession().update(entity);
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getCurrentSession().delete(entity);
	}

	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = get(id);
		getCurrentSession().delete(entity);
	}

	public void deleteByIds(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = get(id);
			getCurrentSession().delete(entity);
		}
	}

	public void deleteByIds(List<PK> ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = get(id);
			getCurrentSession().delete(entity);
		}
	}

	public void flush() {
		getCurrentSession().flush();
	}

	public void clear() {
		getCurrentSession().clear();
	}

	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getCurrentSession().evict(object);
	}

	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString, "queryString must not be empty");
		Query query = getCurrentSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	public Query createQuery(String queryString, Map<String, ?> values) {
		Assert.hasText(queryString, "queryString must not be empty");
		Query query = getCurrentSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getCurrentSession().createCriteria(this.entityClass);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return criteria;
	}

	public void initEntity(T entity) {
		Hibernate.initialize(entity);
	}

	public void initEntity(List<T> entityList) {
		for (Iterator<T> it = entityList.iterator(); it.hasNext();) {
			Hibernate.initialize(it.next());
		}
	}

	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	public Criteria distinct(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	public String getIdName() {
		ClassMetadata meta = sessionFactory.getClassMetadata(this.entityClass);
		return meta.getIdentifierPropertyName();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(new Criterion[] { criterion }).uniqueResult();
	}

	public List<T> findBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(new Criterion[] { criterion });
	}

	public List<T> findByIds(List<PK> ids) {
		return find(new Criterion[] { Restrictions.in(getIdName(), ids) });
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> find(String hql, Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	@SuppressWarnings("unchecked")
	public <X> X findUnique(String hql, Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <X> X findUnique(String hql, Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	public int batchExecute(String hql, Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	public int batchExecute(String hql, Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public T findUnique(Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(Criterion[] criterions) {
		return createCriteria(criterions).list();
	}

	// Page Pagination ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Page<T> getAll(Page<T> page) {
		return findPage(page, new Criterion[0]);
	}

	@SuppressWarnings("unchecked")
	public Page<T> findPage(Page<T> page, String hql, Object... values) {
		Assert.notNull(page, "page must not be empty");
		Query query = createQuery(hql, values);
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}
		setPageParameter(query, page);
		page.setResult(query.list());
		return page;
	}

	@SuppressWarnings("unchecked")
	public Page<T> findPage(Page<T> page, String hql, Map<String, ?> values) {
		Assert.notNull(page, "page must not be empty");
		Query query = createQuery(hql, values);
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}
		setPageParameter(query, page);
		page.setResult(query.list());
		return page;
	}

	@SuppressWarnings("unchecked")
	public Page<T> findPage(Page<T> page, Criterion... criterions) {
		Assert.notNull(page, "page must not be empty");
		Criteria criteria = createCriteria(criterions);
		if (page.isAutoCount()) {
			int totalCount = countCriteriaResult(criteria);
			page.setTotalCount(totalCount);
		}
		setPageParameter(criteria, page);
		page.setResult(criteria.list());
		return page;
	}

	// 设置分页参数到Query对象,辅助函数.
	protected Query setPageParameter(Query query, Page<T> page) {
		Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");
		// hibernate的firstResult的序号从0开始
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		return query;
	}

	// 设置分页参数到Criteria对象,辅助函数.
	protected Criteria setPageParameter(Criteria criteria, Page<T> page) {
		Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");
		// hibernate的firstResult的序号从0开始
		criteria.setFirstResult(page.getFirst() - 1);
		criteria.setMaxResults(page.getPageSize());
		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');
			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");
			for (int i = 0; i < orderByArray.length; i++) {
				if ("asc".equals(orderArray[i]))
					criteria.addOrder(Order.asc(orderByArray[i]));
				else {
					criteria.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return criteria;
	}

	/** 
	 * 执行count查询获得本次Hql查询所能获得的对象总数. 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询. 
	 */
	protected long countHqlResult(String hql, Object... values) {
		String fromHql = hql;
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");
		String countHql = "select count(*) " + fromHql;
		try {
			Long count = (Long) findUnique(countHql, values);
			return count.longValue();
		}
		catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/** 
	 * 执行count查询获得本次Hql查询所能获得的对象总数. 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询. 
	 */
	protected long countHqlResult(String hql, Map<String, ?> values) {
		String fromHql = hql;
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");
		String countHql = "select count(*) " + fromHql;
		try {
			Long count = (Long) findUnique(countHql, values);
			return count.longValue();
		}
		catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	@SuppressWarnings("rawtypes")
	protected int countCriteriaResult(Criteria criteria) {
		CriteriaImpl impl = (CriteriaImpl) criteria;
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List orderEntries = null;
		Field field = null;
		try {
			for (Class superClass = impl.getClass(); superClass != Object.class; superClass = superClass
					.getSuperclass()) {
				try {
					field = superClass.getDeclaredField("orderEntries");
				}
				catch (NoSuchFieldException e) {
					// Field不在当前类定义,继续向上转型
				}
			}
			if (field == null) {
				throw new IllegalArgumentException("Could not find field [" + orderEntries + "] on target [" + impl
						+ "]");
			}
			orderEntries = (List) ReflectionUtils.getField(field, impl);
			ReflectionUtils.setField(field, impl, new ArrayList());
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			criteria.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setField(field, impl, orderEntries);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return totalCount;
	}



	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (((e instanceof IllegalAccessException)) || ((e instanceof IllegalArgumentException))
				|| ((e instanceof NoSuchMethodException))) {
			return new IllegalArgumentException("Reflection Exception.", e);
		}
		if ((e instanceof InvocationTargetException))
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		if ((e instanceof RuntimeException)) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}

	// hibernate Base Dao ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@SuppressWarnings("unchecked")
	public T get(Class<?> clazz, PK id) {
		Assert.notNull(id, "id is required");
		return (T) getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public T load(Class<?> clazz, PK id) {
		Assert.notNull(id, "id is required");
		return (T) getCurrentSession().load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getByIds(Class<?> clazz, PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + clazz.getName() + " as model where model.id in(:ids)";
		return getCurrentSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	public T getUniqueBy(Class<?> clazz, String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + clazz.getName() + " as model where model." + propertyName + " = ?";
		return (T) getCurrentSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> getListBy(Class<?> clazz, String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + clazz.getName() + " as model where model." + propertyName + " = ?";
		return getCurrentSession().createQuery(hql).setParameter(0, value).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<?> clazz) {
		String hql = "from " + clazz.getName();
		return getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllOrderBy(Class<?> clazz, String propertyName) {
		String hql = "from " + clazz.getName() + " order by ? asc";
		return getCurrentSession().createQuery(hql).setParameter(0, propertyName).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllOrderBy(Class<?> clazz, String propertyName, boolean isDesc) {
		if (!isDesc) {// 如果是顺序
			return getAllOrderBy(clazz, propertyName);
		}
		else {
			String hql = "from " + clazz.getName() + " order by ? desc";
			return getCurrentSession().createQuery(hql).setParameter(0, propertyName).list();
		}
	}

	public Long getTotalCount(Class<?> clazz) {
		String hql = "select count(*) from " + clazz.getName();
		return (Long) getCurrentSession().createQuery(hql).uniqueResult();
	}

	public boolean isPropertyUnique(Class<?> clazz, String propertyName, Object oldValue, Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
				return true;
			}
		}
		return (getUniqueBy(clazz, propertyName, newValue) == null);
	}

	public boolean isExisting(Class<?> clazz, String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		return (getUniqueBy(clazz, propertyName, value) != null);
	}

	@SuppressWarnings("unchecked")
	public PK save(Class<?> clazz, T entity) {
		Assert.notNull(entity, "entity is required");
		return (PK) getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Class<?> clazz, T entity) {
		Assert.notNull(entity, "entity is required");
		getCurrentSession().saveOrUpdate(entity);
	}

	public void update(Class<?> clazz, T entity) {
		Assert.notNull(entity, "entity is required");
		getCurrentSession().update(entity);
	}

	public void delete(Class<?> clazz, T entity) {
		Assert.notNull(entity, "entity is required");
		getCurrentSession().delete(entity);
	}

	public void delete(Class<?> clazz, PK id) {
		Assert.notNull(id, "id is required");
		T entity = load(clazz, id);
		getCurrentSession().delete(entity);
	}

	public void deleteByIds(Class<?> clazz, PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = load(clazz, id);
			getCurrentSession().delete(entity);
		}
	}

	public void deleteByIds(Class<?> clazz, List<PK> ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = load(clazz, id);
			getCurrentSession().delete(entity);
		}
	}

	public void evict(Class<?> clazz, Object object) {
		Assert.notNull(object, "object is required");
		getCurrentSession().evict(object);
	}

	public void initEntity(Class<?> clazz, T entity) {
		Hibernate.initialize(entity);
	}

	public void initEntity(Class<?> clazz, List<T> entityList) {
		for (Iterator<T> it = entityList.iterator(); it.hasNext();) {
			Hibernate.initialize(it.next());
		}
	}

	public Criteria createCriteria(Class<?> clazz, Criterion... criterions) {
		Criteria criteria = getCurrentSession().createCriteria(clazz);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return criteria;
	}

	public String getIdName(Class<?> clazz) {
		ClassMetadata meta = sessionFactory.getClassMetadata(clazz);
		return meta.getIdentifierPropertyName();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueBy(Class<?> clazz, String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(clazz, new Criterion[] { criterion }).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(Class<?> clazz, Criterion[] criterions) {
		return createCriteria(clazz, criterions).list();
	}

	public List<T> findBy(Class<?> clazz, String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(clazz, new Criterion[] { criterion });
	}

	public List<T> findByIds(Class<?> clazz, List<PK> ids) {
		return find(clazz, new Criterion[] { Restrictions.in(getIdName(), ids) });
	}

	// Page Pagination ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@SuppressWarnings("unchecked")
	public Page<T> findPage(Class<?> clazz, Page<T> page, Criterion... criterions) {
		Assert.notNull(page, "page must not be empty");
		Criteria criteria = createCriteria(clazz, criterions);
		if (page.isAutoCount()) {
			int totalCount = countCriteriaResult(criteria);
			page.setTotalCount(totalCount);
		}
		setPageParameter(criteria, page);
		page.setResult(criteria.list());
		return page;
	}

	public Page<T> getAll(Class<?> clazz, Page<T> page) {
		return findPage(clazz, page, new Criterion[0]);
	}

	public void updateOrDelete(String hql, Map<String, Object> values) {
		Assert.hasText(hql, "hql must not be empty");
		Query query = getCurrentSession().createQuery(hql);
		if (values != null) {
			query.setProperties(values);
		}
		query.executeUpdate();
	}

}