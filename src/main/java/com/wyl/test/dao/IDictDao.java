package com.wyl.test.dao;

import com.wyl.test.entity.Dict;

import java.util.List;

public interface IDictDao {
	
	public List<Dict> getAll();
	
	public List<Dict> queryByPrefix(String propertyName,String prefix);
	
	public Dict queryWordByExactSearch(String word);
	
	public List<Dict> getAllDictNotNull();
	
	public void update(Dict dict);
	
}
