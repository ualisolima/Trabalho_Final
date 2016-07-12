package br.ufc.trabalho_final.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AutorizadorInteceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String uri = request.getRequestURI();
		if(uri.contains("inserirUsuario")
				|| uri.endsWith("/") ||
		   uri.contains("efetuarLogin") || 
		   uri.endsWith("efetuarLogout") || 
		   uri.endsWith("listarClassificado") ||
		   uri.contains("visualizarNoticia") ||
		   uri.contains("listarNoticiaSecao")){
			return true;
		}
		
		if(request.getSession().getAttribute("usuario_logado")!=null){
			return true;
		}
		
		response.sendRedirect("efetuarLoginFormulario");
		System.out.println(uri);
		return false;
	}
}
