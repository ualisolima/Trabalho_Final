package br.ufc.trabalho_final.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.trabalho_final.dao.ISecaoDAO;

@Controller
public class RootController {

	@Autowired
	private ISecaoDAO secaoDAO;
	
	@RequestMapping("/")
	public String home(HttpSession session, Model model, String msg){
		model.addAttribute("mensagem", msg);
		session.setAttribute("secoes", secaoDAO.findAll());
		
		return "home";
	}
}
