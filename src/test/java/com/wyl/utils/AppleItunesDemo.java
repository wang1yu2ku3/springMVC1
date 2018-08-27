package com.wyl.utils;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AppleItunesDemo {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String Base_Url = "file:///Users/wangyulin/Desktop/黄帝内经与养生智慧 by 浩然居士 on iTunes.htm";
		final WebClient webClient=new WebClient();
		final HtmlPage page=webClient.getPage(Base_Url);
		
		HtmlDivision tbody = (HtmlDivision) page.getByXPath("//tr").get(0);
		String tbody_html = tbody.asXml().toString().trim();
		System.out.println(tbody_html);
	}

}
