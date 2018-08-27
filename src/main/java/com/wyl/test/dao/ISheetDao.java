package com.wyl.test.dao;

import com.wyl.test.entity.Sheet;

import java.util.List;

public interface ISheetDao {
	
	public List<Sheet> getAll();
	
	public Sheet getById(long id);
	
	public List<Sheet> get(Sheet sheet);
	
	public void deleteSheet(Sheet sheet);
	
}
