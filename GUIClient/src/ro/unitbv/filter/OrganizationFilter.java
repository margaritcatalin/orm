package ro.unitbv.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.unitbv.beans.LoginBean;

@WebFilter("/organization/*")
public class OrganizationFilter implements Filter {

	public static final String LOGIN_PAGE = "/index.xhtml";

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		System.out.println("filter called");
		LoginBean loginBean = (LoginBean) httpServletRequest.getSession().getAttribute("loginBean");

		if (Objects.nonNull(loginBean) && Objects.nonNull(loginBean.getIdentityDTO())) {
			// and has the role admin

			System.out.println("org test logged");
			filterChain.doFilter(servletRequest, servletResponse);
		} else {

			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + LOGIN_PAGE);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {

	}
}