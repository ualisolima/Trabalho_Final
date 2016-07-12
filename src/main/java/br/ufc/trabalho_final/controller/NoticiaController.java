package br.ufc.trabalho_final.controller;


import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import br.ufc.trabalho_final.dao.IComentarioDAO;
import br.ufc.trabalho_final.dao.INoticiaDAO;
import br.ufc.trabalho_final.dao.ISecaoDAO;
import br.ufc.trabalho_final.dao.IUsuarioDAO;
import br.ufc.trabalho_final.model.Comentario;
import br.ufc.trabalho_final.model.Noticia;
import br.ufc.trabalho_final.model.Secao;
import br.ufc.trabalho_final.model.Usuario;
import br.ufc.trabalho_final.util.FileUtil;

@Controller
@Transactional
public class NoticiaController {

	@Autowired
	INoticiaDAO noticiaDAO;
	
	@Autowired
	ISecaoDAO secaoDAO;
	
	@Autowired
	IUsuarioDAO usuarioDAO;
	
	@Autowired
	IComentarioDAO comentarioDAO;
	
	@Autowired
	private ServletContext sc;
	
	@RequestMapping("/inserirNoticiaFormulario")
	public String inserirNoticiaFormulario(Model model, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(2L))
			return "redirect:/";
		List<Secao> secoes = secaoDAO.findAll();
		model.addAttribute("secoes", secoes);
		return "usuario/jornalista/inserir_noticia_formulario";
	}
	
	@RequestMapping("/inserirNoticia")
	public String inserirNoticia(Noticia noticia, HttpSession session, @RequestParam(value="image",required=false)MultipartFile image){
		noticia.setUsuario((Usuario)session.getAttribute("usuario_logado"));
		noticia.setSecao(secaoDAO.findOne(noticia.getId_secao()));
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		noticia.setData_noticia(ts);
		noticiaDAO.save(noticia);
		if (image != null && !image.isEmpty()){
			String path = sc.getRealPath("/")+"resources/images/noticia/"+noticia.getId_noticia().toString()+".jpg";
			FileUtil.saveFile(path, image);
		}
		System.out.println(noticia.getId_noticia());
		return "redirect:listarNoticia?msg=Notícia inserida com sucesso";
	}
	
	@RequestMapping("/listarNoticiaSecao")
	public String listarNoticiaSecao(Model model, Long id_secao ,String msg){
		List<Noticia> noticias = Lists.reverse(noticiaDAO.findBySecaoLike(secaoDAO.findOne(id_secao)));
		model.addAttribute("noticias", noticias);
		model.addAttribute("mensagem", msg);
		return "usuario/listar_noticia_secao";
	}
	
	@RequestMapping("/listarNoticia")
	public String listarNoticia(Model model, HttpSession session, String msg){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(2L) && !u.getId_papel().equals(1L))
			return "redirect:/";
		List<Noticia> noticias = Lists.reverse(noticiaDAO.findByUsuarioLike(u));
		model.addAttribute("noticias", noticias);
		model.addAttribute("mensagem", msg);
		return "usuario/jornalista/listar_noticia";
	}
	
	@RequestMapping("/listarNoticiaAdm")
	public String listarNoticiaAdm(Model model, HttpSession session, String msg){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		List<Noticia> noticias = Lists.reverse(noticiaDAO.findAll());
		model.addAttribute("noticias", noticias);
		model.addAttribute("mensagem", msg);
		return "usuario/administrador/listar_noticia_adm";
	}
	
	@RequestMapping("/alterarNoticiaFormulario")
	public String alterarNoticiaFormulario(Long id_noticia, Model model, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(2L) && !u.getId_papel().equals(1L))
			return "redirect:/";
		Noticia noticia = noticiaDAO.findOne(id_noticia);
		if(u != null && !noticia.getId_usuario().equals(u.getId_usuario()) && !u.getId_papel().equals(1L))
			return "redirect:/";
		List<Secao> secoes = secaoDAO.findAll();
		model.addAttribute("secoes", secoes);
		model.addAttribute("noticia", noticia);
		return "usuario/jornalista/alterar_noticia_formulario";
	}
	@RequestMapping("/alterarNoticiaFormularioAdm")
	public String alterarNoticiaFormularioAdm(Long id_noticia, Model model, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		Noticia noticia = noticiaDAO.findOne(id_noticia);
		List<Secao> secoes = secaoDAO.findAll();
		model.addAttribute("secoes", secoes);
		model.addAttribute("noticia", noticia);
		return "usuario/administrador/alterar_noticia_formulario_adm";
	}
	
	@RequestMapping("/visualizarNoticia")
	public String visualizarNoticia(Long id_noticia, Model model, String msg){
		Noticia n = noticiaDAO.findOne(id_noticia);
		List<Comentario> comentarios = Lists.reverse(comentarioDAO.findByNoticiaLike(n));
		model.addAttribute("noticia", n);
		model.addAttribute("mensagem", msg);
		model.addAttribute("comentarios", comentarios);
		return "usuario/visualizar_noticia";
	}
	
	@RequestMapping("/alterarNoticia")
	public String alterarNoticia(Noticia noticia, HttpSession session, @RequestParam(value="image",required=false)MultipartFile image){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		noticia.setUsuario(u);
		noticia.setSecao(secaoDAO.findOne(noticia.getId_secao()));
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		noticia.setData_noticia(ts);
		noticiaDAO.save(noticia);
		if (image != null && !image.isEmpty()){
			String path = sc.getRealPath("/")+"resources/images/noticia/"+noticia.getId_noticia().toString()+".jpg";
			FileUtil.saveFile(path, image);
		}
		return "redirect:listarNoticia?msg=Notícia alterada com sucesso";
	}
	
	@RequestMapping("/alterarNoticiaAdm")
	public String alterarNoticiaAdm(Noticia noticia, HttpSession session, @RequestParam(value="image",required=false)MultipartFile image){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		noticia.setUsuario(u);
		noticia.setSecao(secaoDAO.findOne(noticia.getId_secao()));
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		noticia.setData_noticia(ts);
		noticiaDAO.save(noticia);
		if (image != null && !image.isEmpty()){
			String path = sc.getRealPath("/")+"resources/images/noticia/"+noticia.getId_noticia().toString()+".jpg";
			FileUtil.saveFile(path, image);
		}
		return "redirect:listarNoticiaAdm?msg=Notícia alterada com sucesso";
	}
	
	@RequestMapping("/apagarNoticia")
	public String excluirNoticia(Long id_noticia, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(2L))
			return "redirect:/";
		System.out.println(id_noticia);
		noticiaDAO.delete(id_noticia);
		return "redirect:listarNoticia?msg=Notícia apagada com sucesso";
	}
	
	@RequestMapping("/apagarNoticiaAdm")
	public String excluirNoticiaAdm(Long id_noticia, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		System.out.println(id_noticia);
		noticiaDAO.delete(id_noticia);
		return "redirect:listarNoticiaAdm?msg=Notícia apagada com sucesso";
	}
	
}
