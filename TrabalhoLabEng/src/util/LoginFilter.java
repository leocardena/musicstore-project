package util;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginMB;

@WebFilter(filterName = "loginFilter")
public class LoginFilter implements Filter, Serializable {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LoginMB loginMB = (LoginMB) ((HttpServletRequest) request).getSession().getAttribute("loginMB");

		HttpServletRequest httpSR = (HttpServletRequest) request;
		String req = httpSR.getRequestURI();

		if (loginMB == null || !loginMB.isLogado()) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/logar.xhtml");
		} else {

			if (loginMB.getTipoUsuarioLogado().equals("cliente")
					&& !req.equals("/TrabalhoLabEng/restrito_usuario/finalizar_compra.xhtml")) {
				String contextPath = ((HttpServletRequest) request).getContextPath();
				((HttpServletResponse) response).sendRedirect(contextPath + "/logar.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		}

	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
