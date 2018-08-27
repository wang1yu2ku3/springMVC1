/**
 * GoodsDaoImpl.java
 * wyk
 * 2018年8月21日
 */
package com.wyl.test.dao.impl;

import com.wyl.test.dao.IGoodsDao;
import com.wyl.test.entity.Goods;
import com.wyl.test.util.Page;
import java.util.Objects;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Repository
@Transactional
public class GoodsDaoImpl extends HibernateBaseDao<Goods, Long> implements IGoodsDao  {

	@Override
	public Goods getByGoodsId(Long goodsId) {
		// TODO Auto-generated method stub
		return (Goods) super.getCurrentSession().get(Goods.class, goodsId);
	}

	@Override
	public Long add(Goods goods) {
		// TODO Auto-generated method stub
		return (Long) super.getCurrentSession().save(goods);
	}

	@Override
	public void updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		super.getCurrentSession().update(goods);
		
	}
	
	@Override
	public void delete(Long goodsId) {
		Goods goods=this.get(goodsId);
		if(Objects.isNull(goods)) {
			return;
		}
		super.getCurrentSession().delete(goodsId);
		
	}
	
	public Page<Goods> Page(Page page){
		StringBuilder hq2=new StringBuilder("from Goods goods where 1=1");
		Map<String,String> params=page.getParams();
		if(Objects.nonNull(params) && params.containsKey("IsBook")) {
			hq2.append("and goods.isBook=:isBook");
		}
		Page<Goods> result=super.findPage(page, hq2.toString(),params);
		return result;
	}
	

}
