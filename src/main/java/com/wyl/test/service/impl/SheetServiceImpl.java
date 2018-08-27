package com.wyl.test.service.impl;

import com.wyl.test.dao.ISheetDao;
import com.wyl.test.entity.Sheet;
import com.wyl.test.service.ISheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SheetServiceImpl extends HibernateBaseService<Sheet, Long> implements ISheetService {

	@Autowired
	@Qualifier("sheetDaoImpl")
	private ISheetDao iSheetDao;
	
	@Override
	public List<Sheet> getAll() {
		return iSheetDao.getAll();
	}

	@Override
	@Transactional
	public void deleteSheet(Sheet sheet) {
		iSheetDao.deleteSheet(sheet);
	}

	@Override
	public Sheet getById(long id) {
		return iSheetDao.getById(id);
	}
	
	@Override
	public List<Sheet> get(Sheet sheet) {
		return iSheetDao.get(sheet);
	}
}
