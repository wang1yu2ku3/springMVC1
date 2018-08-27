/**
 * GoodsCotroller.java
 * wyk
 * 2018年8月26日
 */
package com.wyl.test.controller;


import com.wyl.test.constants.IsBook;
import com.wyl.test.dto.BaseResponse;
import com.wyl.test.entity.Goods;
import com.wyl.test.facade.IGoodsFacade;
import com.wyl.test.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wyk
 *
 */
@RestController
@RequestMapping(value="/goods")
public class GoodsCotroller {
	@Autowired
    @Qualifier("goodsFacadeImpl")
	private IGoodsFacade iGoodsFacade;
	
	 @RequestMapping(value = "page", method = RequestMethod.GET)
	 public BaseResponse<Page<Goods>> page(@RequestParam(value = "pageNo") Integer pageNo,
             @RequestParam(value = "pageSize") Integer pageSize,
             @RequestParam(value = "isBook") IsBook isBook) {
		 Page<Goods> page=new Page<>();
		 page.setPageNo(pageNo);
	        page.setPageSize(pageSize);
	        Map<String, Object> queryParam = new HashMap<>();
	        queryParam.put("isBook", isBook);
	        page.setParams(queryParam);
	        Page result = this.iGoodsFacade.Page(page);
	        BaseResponse<Page<Goods>> baseResponse = new BaseResponse<>(result);
	        return baseResponse;
	 }
	 
	 @RequestMapping(value = "{goodsId}", method = RequestMethod.GET)
	 public BaseResponse get(@PathVariable(value = "goodsId") Long goodsId) {
	        Goods goods = iGoodsFacade.getByGoodsId(goodsId);
	        if(Objects.nonNull(goods)) {
	            return new BaseResponse(goods);
	        }
	        return new BaseResponse(-1000, "用户不存在");
	    }
	 
	 @RequestMapping(value = "", method = RequestMethod.PUT)
	    public BaseResponse add(@RequestBody Goods goods) {
	        if(Objects.isNull(goods)) {
	            return new BaseResponse(-1001, "新增用户信息时参数不能为空");
	        }
	        Goods result = this.iGoodsFacade.add(goods);
	        if(Objects.nonNull(result)) {
	            return new BaseResponse(result);
	        }
	        return new BaseResponse(-1002, "新增用户信息");
	    }

	    @RequestMapping(value = "{goodsId}", method = RequestMethod.POST)
	    public BaseResponse update(@PathVariable(value = "goodsId") Long goodsId,
	                       @RequestBody Goods goods) {
	        goods.setGoodsid(goodsId);
	        Goods result = this.iGoodsFacade.update(goods);
	        return new BaseResponse(result);
	    }

	    @RequestMapping(value = "{goodsId}", method = RequestMethod.DELETE)
	    public BaseResponse delete(@PathVariable(value = "goodsId") Long goodsId) {
	        this.iGoodsFacade.delete(goodsId);
	        return new BaseResponse("删除用户信息成功");
	    }

}
