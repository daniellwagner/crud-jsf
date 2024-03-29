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
		
	}

	@Override
	public void doFilter(ServletRequest servletFilter, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		try {
			this.sf.getCurrentSession().beginTransaction();
			chain.doFilter(servletFilter, servletResponse);
			this.sf.getCurrentSession().getTransaction().commit();
			this.sf.getCurrentSession().close();
			
		} catch (Throwable ex) {
			try {
				if(this.sf.getCurrentSession().getTransaction().isActive()){
					this.sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			
			throw new ServletException();	
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.sf = HibernateUtil.getSession();
		
	}

}
