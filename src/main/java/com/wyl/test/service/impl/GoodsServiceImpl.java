/**
 * GoodsServiceImpl.java
 * wyk
 * 2018年8月21日
 */
package com.wyl.test.service.impl;

import com.wyl.test.dao.IGoodsDao;
import com.wyl.test.entity.Goods;
import com.wyl.test.entity.User;
import com.wyl.test.service.IGoodsService;
import com.wyl.test.util.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * @author wyk
 *
 */

@Service
public class GoodsServiceImpl extends HibernateBaseService<Goods, Long> implements IGoodsService{

	private static final Logger logger=Logger.getLogger(GoodsServiceImpl.class);
	
	 @Autowired
	 @Qualifier("goodsDaoImpl")
	 private IGoodsDao iGoodsDao;
	 
	 
	@Override
	public Goods getByGoodsId(Long goodsId) {
		// TODO Auto-generated method stub
		return iGoodsDao.getByGoodsId(goodsId);
	}

	@Override
	public Goods add(Goods goods) {
		// TODO Auto-generated method stub
		Long goodsId=iGoodsDao.add(goods);
		return iGoodsDao.getByGoodsId(goodsId);
	}

	@Override
	public Goods updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		this.iGoodsDao.updateGoods(goods);
		return goods;
	}

	@Override
	public void delete(Long goodsId) {
		// TODO Auto-generated method stub
		this.iGoodsDao.delete(goodsId);
	}

	
	@Override
    public Page<Goods> Page(Page page) {
        return this.iGoodsDao.Page(page);
    }

}
