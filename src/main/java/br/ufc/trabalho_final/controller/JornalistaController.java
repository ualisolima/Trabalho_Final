package br.ufc.trabalho_final.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.trabalho_final.dao.IPapelDAO;
import br.ufc.trabalho_final.dao.IUsuarioDAO;
import br.ufc.trabalho_final.model.Papel;
import br.ufc.trabalho_final.model.Usuario;

@Controller
public class JornalistaController {
	
	@Autowired
	IUsuarioDAO usuarioDAO;
	
	@Autowired
	IPapelDAO papelDAO;
	
	@RequestMapping("/menuJornalista")
	public String menuJornalista(HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(2L))
			return "redirect:/";
		return "usuario/jornalista/menu_jornalista";
	}
	
	@RequestMapping("/listarJornalista")
	public String listarJornalista(String msg, Model model, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		Papel p = papelDAO.findOne(2L);
		List<Usuario> jornalistas = usuarioDAO.findByPapelLike(p);
		model.addAttribute("jornalistas", jornalistas);
		model.addAttribute("mensagem", msg);
		return "usuario/administrador/listar_jornalista";
	}
}
