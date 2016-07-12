package br.ufc.trabalho_final.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.trabalho_final.dao.IPapelDAO;
import br.ufc.trabalho_final.model.Papel;
import br.ufc.trabalho_final.model.Usuario;

@Controller
public class AdiministradorController {
	
	@Autowired
	IPapelDAO papelDAO;
	
	@RequestMapping("/menuAdministrador")
	public String menuAdministrador(HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		return "usuario/administrador/menu_administrador";
	}
	
	@RequestMapping("/inserirJornalistaFormulario")
	public String inserirJornalistaFormulario(HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		Papel p = papelDAO.findOne(2L);
		session.setAttribute("papel", p);
		return "usuario/inserir_usuario_formulario";
	}
	
	@RequestMapping("/inserirEditorFormulario")
	public String inserirEditorFormulario(HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		Papel p = papelDAO.findOne(1L);
		session.setAttribute("papel", p);
		return "usuario/inserir_usuario_formulario";
	}

}
