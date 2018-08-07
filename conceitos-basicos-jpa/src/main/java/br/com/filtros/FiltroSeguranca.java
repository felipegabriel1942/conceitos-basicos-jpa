package br.com.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.controle.ControleLogin;

@WebFilter(urlPatterns = "/privado/*")
public class FiltroSeguranca implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession sessao = httpRequest.getSession();
		String contextPath = httpRequest.getContextPath();
		ControleLogin controleLogin = (ControleLogin) sessao.getAttribute("controleLogin");

		if (controleLogin == null || controleLogin.getUsuarioLogado() == null) {
			httpResponse.sendRedirect(contextPath + "/login.xhtml");
		} else {
			
			//Vericação de autorização de acesso
			//Para que o filtro de segurança funcione a pagina tem que ser redirecionada
			String pagina = httpRequest.getRequestURL().toString();
			if (pagina.contains("/privado/funcionario")) {
				if (!controleLogin.getUsuarioLogado().getGrupo().getNome().equals("Administrador")) {
					httpResponse.sendRedirect(contextPath + "/naoAutorizado.xhtml");
				}
			}
		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
