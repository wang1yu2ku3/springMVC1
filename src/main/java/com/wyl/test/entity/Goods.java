/**
 * goods.java
 * wyk
 * 2018年8月20日
 */
package com.wyl.test.entity;

import java.util.Date;
import javax.persistence.*;

import com.wyl.test.constants.IsBook;

@Entity
@Table(name= "goods")
public class Goods {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "goodsId")
	@Id
	private Long goodsid;
	
	 @Column(nullable = false) //该字段名称不能重复
	 private String goodsName;
	 
	 private String marketPrice;
	 
	 @Column
	 @Enumerated(EnumType.STRING)
	 private IsBook isBook;
	 
	 private Date saleTime;

	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public IsBook getIsBook() {
		return isBook;
	}

	public void setIsBook(IsBook isBook) {
		this.isBook = isBook;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}
	 
	 

}
