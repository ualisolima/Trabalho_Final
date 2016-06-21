package br.ufc.trabalho_final.dao;

import java.util.List;

import br.ufc.trabalho_final.model.Noticia;

public interface INoticiaEnhaced {
	
	public List<Noticia> findByTituloLike(String titulo);

}
