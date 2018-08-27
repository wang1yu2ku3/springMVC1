/**
 * IGoodsDao.java
 * wyk
 * 2018年8月21日
 */
package com.wyl.test.dao;

import com.wyl.test.util.Page;
import com.wyl.test.entity.Goods;

public interface IGoodsDao {
	Goods getByGoodsId( Long goodsId); 	
	
	Long add(Goods goods);
	
	void updateGoods(Goods goods);

	void delete(Long goodsId);
	
	Page<Goods> Page(Page page);
}
