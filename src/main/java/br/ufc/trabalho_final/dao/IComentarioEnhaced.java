package br.ufc.trabalho_final.dao;

import java.util.List;

import br.ufc.trabalho_final.model.Comentario;
import br.ufc.trabalho_final.model.Noticia;

public interface IComentarioEnhaced {
	
	public List<Comentario> findByNoticiaLike(Noticia noticia);

}
