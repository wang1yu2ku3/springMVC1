package com.wyl.test.dao.impl;

import com.wyl.test.dao.ISheetDao;
import com.wyl.test.entity.Sheet;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class SheetDaoImpl extends HibernateBaseDao<Sheet, Long> implements ISheetDao {

	@Override
	public List<Sheet> getAll() {
		return super.getAll();
	}

	public void deleteSheet(Sheet sheet) {
		super.delete(sheet.getId());
	}
	
	@Override
	public Sheet getById(long id) {
		return super.get(id);
	}
	
	@Override
	public List<Sheet> get(Sheet sheet) {
		return super.getListBy(Sheet.class, "供应商名称", sheet.get供应商名称());
	}
}
