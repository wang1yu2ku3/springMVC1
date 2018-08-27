package com.wyl.test.dao.impl;

import com.wyl.test.dao.IDictDao;
import com.wyl.test.entity.Dict;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class DictDaoImpl extends HibernateBaseDao<Dict, Long> implements IDictDao {

	@Override
	public List<Dict> getAll() {
		return super.getAll();
	}
	
	@Override
	public List<Dict> queryByPrefix(String propertyName, String prefix) {
		return super.getListByPrefix(propertyName, prefix);
	}
	
	@Override
	public Dict queryWordByExactSearch(String word) {
		return super.getUniqueBy("word", word);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dict> getAllDictNotNull() {
		String hql = "from " + entityClass.getName() + " as model where model.flag is '' and model.word not like '%.%' order by model.word";
		return super.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	@Transactional
	public void update(Dict dict) {
		super.update(dict);
	}

}
