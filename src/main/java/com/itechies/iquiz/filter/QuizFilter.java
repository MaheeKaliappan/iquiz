package com.itechies.iquiz.filter;

import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuizFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		String path = req.getServletPath();
		String server = req.getScheme()+"://"+ req.getServerName()+":"+req.getServerPort();
		 String url = ((HttpServletRequest)request).getRequestURL().toString();
		 String queryString = ((HttpServletRequest)request).getQueryString();
//		System.out.println("p "+path);
//		System.out.println("u "+url);
//		System.out.println("q "+queryString);
//		System.out.println("userName "+session.getAttribute("userName"));
		
		 String p = req.getRequestURI();

		// System.out.println("server "+server+"/iquiz/login");
	       
		if (!path.equals("/login"))
		{
			
			if (path.equals("/home"))chain.doFilter(request, response);
			else if (path.equals("/logout"))chain.doFilter(request, response);

			else if(session.getAttribute("userName") == null)
				{
					
					res.sendRedirect(server+"/iquiz/login");
					System.out.println("if");
//					RequestDispatcher rd=request.getRequestDispatcher("/login");
//					rd.forward(request, response);
				}
				else if(session.getAttribute("userName") != null){
					RequestDispatcher rd=request.getRequestDispatcher("/home");
					rd.forward(request, response);
					System.out.println("else");
				}
		}
		
		else
			chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
