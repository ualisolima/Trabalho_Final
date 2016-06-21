package br.ufc.trabalho_final.dao;

import java.util.List;

import br.ufc.trabalho_final.model.Papel;

public interface IPapelEnhaced {
	
	public List<Papel> findByPapelLike(String papel);
	
}
