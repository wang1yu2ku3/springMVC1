/**
 * IgoodsService.java
 * wyk
 * 2018年8月21日
 */
package com.wyl.test.service;

import com.wyl.test.util.Page;
import com.wyl.test.entity.Goods;

/**
 * @author wyk
 *
 */
public interface IGoodsService {
    Goods getByGoodsId( Long goodsId); 	
	
	Goods add(Goods goods);
	
	Goods updateGoods(Goods goods);

	void delete(Long goodsId);
	
	Page<Goods> Page(Page page);

}
