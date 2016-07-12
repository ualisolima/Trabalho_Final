package br.ufc.trabalho_final.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.trabalho_final.criptografia.Criptografia;
import br.ufc.trabalho_final.dao.IPapelDAO;
import br.ufc.trabalho_final.dao.IUsuarioDAO;
import br.ufc.trabalho_final.model.Papel;
import br.ufc.trabalho_final.model.Usuario;
import br.ufc.trabalho_final.util.FileUtil;

@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioDAO usuarioDAO ;
	
	@Autowired
	private IPapelDAO papelDAO;
	
	@Autowired
	private ServletContext sc;
	
	@RequestMapping("/inserirUsuarioFormulario")
	public String inserirUsuarioFormulario(HttpSession session){
		Papel p = papelDAO.findOne(3L);
		session.setAttribute("papel", p);
		return "usuario/inserir_usuario_formulario";
	}
	
	
	@RequestMapping("/inserirUsuario")
	public String inserirUsuario(Usuario usuario, HttpSession session, @RequestParam(value="image",required=false)MultipartFile image){
		System.out.println(usuario.getId_papel() + " : " +usuario.getLogin());
		if (usuario.getPapel() == null)
			usuario.setPapel(papelDAO.findOne(usuario.getId_papel()));
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L) && (usuario.getId_papel().equals(2L) || usuario.getId_papel().equals(1L) ))
			return "redirect:/";
		usuario.setSenha(Criptografia.codifica(usuario.getSenha()));
		System.out.println(usuario.getSenha());
		usuarioDAO.save(usuario);
		if (image != null && !image.isEmpty()){
			String path = sc.getRealPath("/")+"resources/images/usuario/"+usuario.getId_usuario().toString()+".jpg";
			FileUtil.saveFile(path, image);
		}
		if ((Usuario)session.getAttribute("usuario_logado") == null)
			session.setAttribute("usuario_logado", usuario);
		return "redirect:/?msg=Cadastro inserido com sucesso";
	}
	
	@RequestMapping("/alterarUsuarioFormulario")
	public String alterarUsuarioFormulario(Long id_usuario, Model model, HttpSession session){
		Usuario usuario = usuarioDAO.findOne(id_usuario);
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L) && !usuario.getId_usuario().equals(u.getId_usuario()))
			return "redirect:/";
		usuario.setSenha(Criptografia.decodifica(usuario.getSenha()));
		model.addAttribute("usuario", usuario);
		return "usuario/alterar_usuario_formulario";
	}
	
	@RequestMapping("/alterarUsuario")
	public String alterarUsuario(Usuario usuario, HttpSession session, @RequestParam(value="image",required=false)MultipartFile image){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		usuario.setPapel(papelDAO.findOne(usuario.getId_papel()));
		System.out.println(usuario.getSenha());
		usuario.setSenha(Criptografia.codifica(usuario.getSenha()));
		usuarioDAO.save(usuario);
		if (image != null && !image.isEmpty()){
			String path = sc.getRealPath("/")+"resources/images/usuario/"+usuario.getId_usuario().toString()+".jpg";
			FileUtil.saveFile(path, image);
		}
		if(u.getId_papel() == 1L) {
			return "redirect:listarJornalista?msg=Cadastro alterado com sucesso";
		} else {
			return "redirect:/?msg=Cadastro alterado com sucesso";
		}
	}
}
