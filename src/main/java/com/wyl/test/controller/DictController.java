package com.wyl.test.controller;

import com.wyl.test.entity.Dict;
import com.wyl.test.facade.IDictFacade;
import com.wyl.test.facade.ISheetFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/dict")
public class DictController {
	
	@Autowired
	@Qualifier("dictFacadeImpl")
	private IDictFacade iDictFacade;
	
	@Autowired
	@Qualifier("sheetFacadeImpl")
	private ISheetFacade iSheetFacade;
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	@ResponseBody
	public List<Dict>  test() {
		List<Dict> list = iDictFacade.getAll();
		return list;
		//iSheetFacade.process();
		//return new ArrayList<Sheet>();
	}
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public List<Dict> queryDict(@RequestParam(value="q",defaultValue="")String prefix) {
		List<Dict> list = iDictFacade.queryByPrefix("word", prefix);
		return list;
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	@ResponseBody
	public Dict searchDict(@RequestParam(value="wd",defaultValue="")String word) {
		Dict dict = iDictFacade.queryWordByExactSearch(word);
		return dict;
	}
	
	@RequestMapping(value="/getNetDict",method=RequestMethod.GET)
	@ResponseBody
	public void getNetDict(){
		iDictFacade.getNetDict();
	}
	
}
