package model;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
	String nofilter;
	String[] nofilterFiles;
	String sendRedirect;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		String basePath = req.getContextPath();
		
		if(isInArray(path,nofilterFiles)){	
			chain.doFilter(request, response);
		}else if((session.getAttribute("admin")!=null) || (session.getAttribute("keeper")!=null)){		
			chain.doFilter(request, response);
		}else{
			res.sendRedirect(basePath + sendRedirect);
			return;
		}
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		nofilter = filterConfig.getInitParameter("noFilter");
		nofilterFiles = nofilter.split(",");
		sendRedirect = filterConfig.getInitParameter("sendRedirect");

	}
	
	private boolean isInArray(String path, String[] nofilterFiles){
		for(int k=0;k<nofilterFiles.length;k++){
			String nofilter = nofilterFiles[k];
			if(path.equals(nofilter)){
				return true;
			}
		}
		return false;
	}

}
