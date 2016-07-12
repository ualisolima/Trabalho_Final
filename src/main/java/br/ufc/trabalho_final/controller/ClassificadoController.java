package br.ufc.trabalho_final.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import br.ufc.trabalho_final.dao.IClassificadoDAO;
import br.ufc.trabalho_final.model.Classificado;
import br.ufc.trabalho_final.model.Usuario;
import br.ufc.trabalho_final.util.FileUtil;

@Controller
public class ClassificadoController {
	
	@Autowired
	private IClassificadoDAO classificadoDAO;
	
	@Autowired
	private ServletContext sc;
	
	@RequestMapping("/inserirClassificadoFormulario")
	public String inserirClassificadoFormulario(HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		return "usuario/administrador/inserir_classificado_formulario";
	}
	
	@RequestMapping("/inserirOfertaClassificadoFormulario")
	public String inserirOfertaClassificadoFormulario(Long id_classificado, Model model, String msg, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(3L))
			return "redirect:/";
		Classificado c = classificadoDAO.findOne(id_classificado);
		model.addAttribute("classificado", c);
		model.addAttribute("mensagem",msg);
		return "usuario/inserir_oferta_classificado_formulario";
	}
	
	@RequestMapping("/inserirOfertaClassificado")
	public String inserirOfertaClassificado(Classificado classificado, HttpSession session){
		System.out.println(classificado.getPreco() + " : " + classificado.getTexto());
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		Classificado c = classificadoDAO.findOne(classificado.getId_classificado());
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		if (c.getMelhor_oferta() == null && c.getPreco() <= classificado.getMelhor_oferta()){
			classificado.setUsuario(u);
			classificado.setData_oferta(ts);
			classificadoDAO.save(classificado);
			return "redirect:listarClassificado?msg=Oferta inserida com sucesso";
		} else if (c.getMelhor_oferta() != null && c.getMelhor_oferta() < classificado.getMelhor_oferta()){
			classificado.setUsuario(u);
			classificado.setData_oferta(ts);
			classificadoDAO.save(classificado);
			return "redirect:listarClassificado?msg=Oferta inserida com sucesso";
		}
		return "redirect:inserirOfertaClassificadoFormulario?id_classificado="+c.getId_classificado()+"&msg=Valor precisa ser maior que o valor mínimo e maior que o último valor já ofertado";
	}
	
	@RequestMapping("/alterarClassificadoFormulario")
	public String alterarClassificadoFormulario(Long id_classificado, Model model, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario_logado");
		if (u != null && !u.getId_papel().equals(1L))
			return "redirect:/";
		Classificado c = classificadoDAO.findOne(id_classificado);
		model.addAttribute("classificado", c);
		return "usuario/administrador/alterar_classificado_formulario";
	}
	
	@RequestMapping("/inserirClassificado")
	public String inserirClassificado(Classificado classificado, @RequestParam(value="image",required=false)MultipartFile image){
		classificadoDAO.save(classificado);
		if (image != null && !image.isEmpty()){
			String path = sc.getRealPath("/")+"resources/images/classificado/"+classificado.getId_classificado().toString()+".jpg";
			FileUtil.saveFile(path, image);
		}
		return "redirect:listarClassificado?msg=Classificado inserido com sucesso";
	}
	
	@RequestMapping("/alterarClassificado")
	public String alterarClassificado(Classificado classificado, @RequestParam(value="image",required=false)MultipartFile image){
		classificadoDAO.save(classificado);
		if (image != null && !image.isEmpty()){
			String path = sc.getRealPath("/")+"resources/images/classificado/"+classificado.getId_classificado().toString()+".jpg";
			FileUtil.saveFile(path, image);
		}
		return "redirect:listarClassificado?msg=Classificado alterado com sucesso";
	}
	
	@RequestMapping("/listarClassificado")
	public String listarClassificado(Model model, String msg){
		List<Classificado> classificados = classificadoDAO.findAll();
		model.addAttribute("classificados", classificados);
		model.addAttribute("mensagem", msg);
		return "usuario/listar_classificado";
	}
	
	@RequestMapping("/apagarClassificado")
	public String apagarClassificado(Long id_classificado){
		classificadoDAO.delete(id_classificado);
		return "redirect:listarClassificado?msg=Classificado apagado com sucesso";
	}
	

}
