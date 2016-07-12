package br.ufc.trabalho_final.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.trabalho_final.dao.IComentarioDAO;
import br.ufc.trabalho_final.dao.INoticiaDAO;
import br.ufc.trabalho_final.dao.IUsuarioDAO;
import br.ufc.trabalho_final.model.Comentario;
import br.ufc.trabalho_final.model.Usuario;

@Controller
public class ComentarioController {
	
	@Autowired
	IComentarioDAO comentarioDAO;
	
	@Autowired
	INoticiaDAO noticiaDAO;
	
	@Autowired
	IUsuarioDAO usuarioDAO;
	
	@RequestMapping("/inserirComentario")
	public String inserirComentario(Comentario comentario, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(3L))
			return "redirect:/";
		
		comentario.setUsuario(usuarioDAO.findOne(comentario.getId_usuario()));
		comentario.setNoticia(noticiaDAO.findOne(comentario.getId_noticia()));
		comentarioDAO.save(comentario);
		return "redirect:visualizarNoticia?msg=Comentário Inserido&id_noticia="+comentario.getId_noticia();
	}
	
	@RequestMapping("/apagarComentario")
	public String apagarComentario(Long id_comentario, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(3L) && !u.getId_papel().equals(1L))
			return "redirect:/";
		Comentario c = comentarioDAO.findOne(id_comentario);
		comentarioDAO.delete(id_comentario);
		return "redirect:visualizarNoticia?msg=Comentário Apagado&id_noticia="+c.getId_noticia();
	}

}
