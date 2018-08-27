package com.wyl.test.entity;

import javax.persistence.*;


@Entity
@Table(name = "sheet1")
public class Sheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;// 主键,必填,数据库的自动增长字段生成
	
	@Column(name = "凭证号")
	private String 凭证号;
	
	@Column(name = "年度")
	private String 年度;
	
	@Column(name = "金额")
	private double 金额;
	
	@Column(name = "供应商")
	private String 供应商;
	
	@Column(name = "供应商名称")
	private String 供应商名称;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String get凭证号() {
		return 凭证号;
	}

	public void set凭证号(String 凭证号) {
		this.凭证号 = 凭证号;
	}

	public String get年度() {
		return 年度;
	}

	public void set年度(String 年度) {
		this.年度 = 年度;
	}

	public double get金额() {
		return 金额;
	}

	public void set金额(double 金额) {
		this.金额 = 金额;
	}

	public String get供应商() {
		return 供应商;
	}

	public void set供应商(String 供应商) {
		this.供应商 = 供应商;
	}

	public String get供应商名称() {
		return 供应商名称;
	}

	public void set供应商名称(String 供应商名称) {
		this.供应商名称 = 供应商名称;
	}
	
	@Override
	public String toString() {
        return "{" + this.id + "--" + this.供应商名称  + "--" + this.年度 + ":" + this.金额  +"}";
    }
}
