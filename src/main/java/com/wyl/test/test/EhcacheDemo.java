package com.wyl.test.test;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.Serializable;

public class EhcacheDemo implements Runnable{

	public static void main(String[] args) {
		String cacheName = "colors";   
		CacheManager manager = new CacheManager("F://dev//springmvcWorspace//springMVC//src//main//resources//ehcache.xml");   
		net.sf.ehcache.Cache cache = manager.getCache(cacheName);
		System.out.println(cache.toString());
		Element element = new Element("key1", "value1");   
	    cache.put(element); 
	    cache.put(new Element("key1", "value2"));
	    element = cache.get("key1");
	    @SuppressWarnings("deprecation")
		Serializable value = element.getValue();
	    System.out.println(value);

	}

	@Override
	public void run() {
		
	}

}
