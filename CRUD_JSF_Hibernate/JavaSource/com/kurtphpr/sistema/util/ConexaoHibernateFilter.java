package com.kurtphpr.sistema.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import com.kurtphpr.sistema.vendas.HibernateUtil;

public class ConexaoHibernateFilter implements Filter {

	private SessionFactory sf;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletFilter, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		this.sf.getCurrentSession().beginTransaction();
		chain.doFilter(servletFilter, servletResponse);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.sf = HibernateUtil.getSession();
		
	}

}
