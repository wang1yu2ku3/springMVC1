package com.wyl.test.util;

import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @fileName       Page.java
 * @description    分页模型工具类,该工具类用于详细的分页和存贮分页数据
 * @author         yulin.wang
 * @since          1.0
 * @createTime     May 5, 2015 12:04:06 PM
 */
public class Page<T> {

	public static final String ASC = "asc";
	public static final String DESC = "desc";
	protected int pageNo = 1;
	protected int pageSize = 1;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;
	protected Map<String, Object> params = new HashMap();

	protected List<T> result = Collections.emptyList();
	protected long totalCount = -1L;

	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Page<T> pageNo(int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1)
			this.pageSize = 1;
	}

	public Page<T> pageSize(int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	public int getFirst() {
		return (this.pageNo - 1) * this.pageSize + 1;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Page<T> orderBy(String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if ((!StringUtils.equals("desc", orderStr)) && (!StringUtils.equals("asc", orderStr))) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}
		this.order = StringUtils.lowerCase(order);
	}

	public Page<T> order(String theOrder) {
		setOrder(theOrder);
		return this;
	}

	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(this.orderBy)) && (StringUtils.isNotBlank(this.order));
	}

	public boolean isAutoCount() {
		return this.autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page<T> autoCount(boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	public List<T> getResult() {
		return this.result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPages() {
		if (this.totalCount < 0L) {
			return -1L;
		}
		long count = this.totalCount / this.pageSize;
		if (this.totalCount % this.pageSize > 0L) {
			count += 1L;
		}
		return count;
	}

	public boolean isHasNext() {
		return this.pageNo + 1 <= getTotalPages();
	}

	public int getNextPage() {
		if (isHasNext()) {
			return this.pageNo + 1;
		}
		return this.pageNo;
	}

	public boolean isHasPre() {
		return this.pageNo - 1 >= 1;
	}

	public int getPrePage() {
		if (isHasPre()) {
			return this.pageNo - 1;
		}
		return this.pageNo;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}