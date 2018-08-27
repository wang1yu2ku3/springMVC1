package com.wyl.test.facade.impl;

import com.wyl.test.entity.Dict;
import com.wyl.test.facade.IDictFacade;
import com.wyl.test.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictFacadeImpl extends HibernateBaseFacade<Dict, Long> implements
		IDictFacade {

	@Autowired
	@Qualifier("dictServiceImpl")
	private IDictService iDictService;
	
	@Override
	public List<Dict> getAll() {
		return iDictService.getAll();
	}
	
	@Override
	public List<Dict> queryByPrefix(String propertyName,String prefix) {
		return iDictService.queryByPrefix(propertyName, prefix);
	}
	
	@Override
	public Dict queryWordByExactSearch(String word) {
		return iDictService.queryWordByExactSearch(word);
	}
	
	@Override
	public void getNetDict() {
		iDictService.getNetDict();
	}

}
