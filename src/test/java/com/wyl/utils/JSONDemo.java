package com.wyl.utils;

import net.sf.json.JSONObject;

public class JSONDemo {

	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("result", "ABC");  
		json.put("msg", "XYZ"); 
		System.out.println(json.toString()); 
	}
}
