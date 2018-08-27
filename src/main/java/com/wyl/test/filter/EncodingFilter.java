package com.wyl.test.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter{
	
	private String encoding = null;
	
	@Override
	public void destroy() {
		encoding = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		String encoding = getEncoding();  
        if (encoding == null){  
            encoding = "utf-8";
        }  
        request.setCharacterEncoding(encoding);// 在请求里设置上指定的编码  
        filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
	}
	
	private String getEncoding() {  
        return this.encoding;  
    }
	
}
