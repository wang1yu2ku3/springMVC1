/**
 * GoodsFacadeImpl.java
 * wyk
 * 2018年8月26日
 */
package com.wyl.test.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wyl.test.entity.Goods;
import com.wyl.test.facade.IGoodsFacade;
import com.wyl.test.service.IGoodsService;
import com.wyl.test.util.Page;


/**
 * @author wyk
 *
 */
@Service(value = "goodsFacadeImpl")
public class GoodsFacadeImpl  extends HibernateBaseFacade<Goods, Long> implements
IGoodsFacade {
	@Autowired
    @Qualifier("goodsServiceImpl")
	private IGoodsService iGoodsService;

	@Override
	public Goods getByGoodsId(Long goodsId) {
		// TODO Auto-generated method stub
		return this.iGoodsService.getByGoodsId(goodsId);
	}

	@Override
	public Goods add(Goods goods) {
		// TODO Auto-generated method stub
		return this.iGoodsService.add(goods);
	}

	@Override
	public Goods update(Goods goods) {
		// TODO Auto-generated method stub
		return this.iGoodsService.updateGoods(goods);
	}

	@Override
	public void delete(Long goodsId) {
		// TODO Auto-generated method stub
		this.iGoodsService.delete(goodsId);
	}

	@Override
	public Page<Goods> Page(Page page) {
		// TODO Auto-generated method stub
		return this.iGoodsService.Page(page);
	}

}
