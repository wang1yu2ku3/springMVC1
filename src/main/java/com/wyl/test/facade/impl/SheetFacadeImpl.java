package com.wyl.test.facade.impl;

import com.wyl.test.entity.Sheet;
import com.wyl.test.facade.ISheetFacade;
import com.wyl.test.service.ISheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SheetFacadeImpl extends HibernateBaseFacade<Sheet, Long> implements ISheetFacade {
	
	@Autowired
	@Qualifier("sheetServiceImpl")
	private ISheetService iSheetService;
	
	public void process() {
		double sum = 2040570738.2399986;
		List<Sheet> original = iSheetService.getAll();
		for(Sheet sheet : original) {
			System.out.println("=========================================");
			Sheet result = iSheetService.getById(sheet.getId());
			if(result != null) {
				Sheet newSheet = new Sheet();
				newSheet.set供应商名称(result.get供应商名称());
				List<Sheet> t1 = iSheetService.get(newSheet);
				if(t1.size() > 0) {
					for(int i = 0; i < t1.size() ; i++) {
						Sheet sheet2 = t1.get(i);
						if((result.get金额() +  sheet2.get金额()) == 0) {
							Sheet q1 = iSheetService.getById(sheet2.getId());
							if(q1 != null) {
								System.out.println("需要删除的数据" + sheet2);
								iSheetService.deleteSheet(sheet2);
							}
							
							Sheet q2 = iSheetService.getById(sheet.getId());
							if(q2 != null) {
								System.out.println("需要删除的数据" + sheet);
								iSheetService.deleteSheet(sheet);
							}
							break;
						}
					}
				}
			}
			//验证平衡
			double t = 0;
			List<Sheet> temp = iSheetService.getAll();
			for(Sheet sh : temp) {
				t+=sh.get金额();
			}
			if(t == sum) {
				
			}else {
				break;
			}
			
		}
		System.out.println("sheet");
	}
	
}
