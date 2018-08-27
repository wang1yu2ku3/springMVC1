package com.wyl.test.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author yulin
 * @category 禁止浏览器缓存动态页面的过滤器
 */
public class NoAllowBrowerCacheFilter implements Filter	{

	private String include;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        
        Pattern contentPattern = Pattern.compile(include);
		Matcher contentMatcher = contentPattern.matcher(requestURI);
		
		if (contentMatcher.find()) {
			filterChain.doFilter(req, resp);
			return;
		}
		resp.setDateHeader("Expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        filterChain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		include = filterConfig.getInitParameter("include");
	}
	
}
