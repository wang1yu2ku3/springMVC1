package com.wyl.test.filter;

import javax.servlet.*;
import java.io.IOException;

public class ClientFilter implements Filter {

	@Override
	public void destroy() {
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException { 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
