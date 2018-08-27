package com.wyl.test.service;

import com.wyl.test.entity.Sheet;

import java.util.List;


public interface ISheetService {
	
	public List<Sheet> getAll();
	
	public void deleteSheet(Sheet sheet);
	
	public Sheet getById(long id);
	
	public List<Sheet> get(Sheet sheet);
	
}
