package com.wyl.test.facade;

import com.wyl.test.entity.Dict;

import java.util.List;

public interface IDictFacade {
	
	public List<Dict> getAll();
	
	public List<Dict> queryByPrefix(String propertyName,String prefix);
	
	public Dict queryWordByExactSearch(String word);
	
	public void getNetDict();
	
}
