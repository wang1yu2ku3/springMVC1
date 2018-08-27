package com.wyl.test.service;

import com.wyl.test.entity.Dict;

import java.util.List;

public interface IDictService {
	
	public List<Dict> getAll();
	
	public List<Dict> queryByPrefix(String propertyName,String prefix);
	
	public Dict queryWordByExactSearch(String word);
	
	public void getNetDict();
	
}
