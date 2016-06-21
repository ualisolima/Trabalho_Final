package br.ufc.trabalho_final.dao;

import java.util.List;

import br.ufc.trabalho_final.model.Secao;

public interface ISecaoEnhaced {
	
	public List<Secao> findByTituloLike(String titulo);

}
