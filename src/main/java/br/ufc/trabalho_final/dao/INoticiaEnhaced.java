package br.ufc.trabalho_final.dao;

import java.util.List;

import br.ufc.trabalho_final.model.Noticia;
import br.ufc.trabalho_final.model.Secao;
import br.ufc.trabalho_final.model.Usuario;

public interface INoticiaEnhaced {
	
	public List<Noticia> findByTituloLike(String titulo);
	
	public List<Noticia> findByUsuarioLike(Usuario usuario);
	
	public List<Noticia> findBySecaoLike(Secao secao);
}
