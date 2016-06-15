package br.ufc.trabalho_final.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.trabalho_final.dao.IUsuarioDAO;
import br.ufc.trabalho_final.form.LoginForm;
import br.ufc.trabalho_final.model.Usuario;

@Controller
public class LoginController {

	@Autowired
	private IUsuarioDAO usuarioDAO ;
	
	@RequestMapping("/efetuarLoginFormulario")
	public String efetuarLoginFormulario(){
		return "efetuar_login_formulario";
	}
	
	@RequestMapping("/efetuarLogin")
	public String efetuarLogin(LoginForm loginForm, HttpSession session){
		
		List<Usuario> usuarios = usuarioDAO.findByLoginLike(loginForm.getLogin());
		if(usuarios!=null && usuarios.size()>0){
			Usuario usuario = usuarios.get(0);
			if(loginForm.getSenha().equals(usuario.getSenha())){
				session.setAttribute("usuario_logado", usuario);
				return "menu";
			}
		}
		
		return "redirect:efetuarLoginFormulario";
	}
	
	@RequestMapping("/efetuarLogout")
	public String efetuarLogout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
}
