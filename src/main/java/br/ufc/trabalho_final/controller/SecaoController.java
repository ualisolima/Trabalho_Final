package br.ufc.trabalho_final.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.trabalho_final.dao.ISecaoDAO;
import br.ufc.trabalho_final.model.Papel;
import br.ufc.trabalho_final.model.Secao;
import br.ufc.trabalho_final.model.Usuario;

@Controller
public class SecaoController {
	
	@Autowired
	ISecaoDAO secaoDAO;
	
	@RequestMapping("/inserirSecaoFormulario")
	public String inserirSecaoFormulario(HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		return "usuario/administrador/inserir_secao_formulario";
	}
	
	@RequestMapping("/alterarSecaoFormulario")
	public String alterarSecaoFormulario(Long id_secao, HttpSession session, Model model){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		Secao sec = secaoDAO.findOne(id_secao);
		model.addAttribute("sec", sec);
		return "usuario/administrador/alterar_secao_formulario";
	}
	
	@RequestMapping("/inserirSecao")
	public String inserirSecao(Secao secao, HttpSession session){
		secaoDAO.save(secao);
		session.setAttribute("secoes", secaoDAO.findAll());
		return "redirect:listarSecao?msg=Secao inserida com sucesso";
	}
	
	@RequestMapping("/alterarSecao")
	public String alterarSecao(Secao secao, HttpSession session){
		secaoDAO.save(secao);
		session.setAttribute("secoes", secaoDAO.findAll());
		return "redirect:listarSecao?msg=Secao alterada com sucesso";
	}
	
	@RequestMapping("/listarSecao")
	public String listarSecao(String msg, Model model, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		model.addAttribute("mensagem", msg);
		return "usuario/administrador/listar_secao";
	}
	
	@RequestMapping("/apagarSecao")
	public String alterarSecao(Long id_secao, HttpSession session){
		secaoDAO.delete(id_secao);
		session.setAttribute("secoes", secaoDAO.findAll());
		return "redirect:listarSecao?msg=Secao apagada com sucesso";
	}

}
