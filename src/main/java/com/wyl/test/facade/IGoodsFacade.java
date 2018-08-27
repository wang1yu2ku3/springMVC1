/**
 * IGoodsFacade.java
 * wyk
 * 2018年8月26日
 */
package com.wyl.test.facade;

import com.wyl.test.entity.Goods;
import com.wyl.test.util.Page;

/**
 * @author wyk
 *
 */
public interface IGoodsFacade {
	Goods getByGoodsId(Long goodsId);
	
	Goods add(Goods goods);
	
	Goods update(Goods goods);
	
	void delete(Long goodsId);
	
	Page<Goods> Page(Page page);

}
