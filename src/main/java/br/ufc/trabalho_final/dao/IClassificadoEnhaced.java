package br.ufc.trabalho_final.dao;

import java.util.List;

import br.ufc.trabalho_final.model.Classificado;

public interface IClassificadoEnhaced {
	
	public List<Classificado> findByTituloLike(String titulo);

}
