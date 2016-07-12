package br.ufc.trabalho_final.dao;

import java.util.List;

import br.ufc.trabalho_final.model.Papel;
import br.ufc.trabalho_final.model.Usuario;


public interface IUsuarioEnhaced {

	//http://docs.spring.io/spring-data/jpa/docs/1.4.3.RELEASE/reference/html/jpa.repositories.html
	public List<Usuario> findByLoginLike(String login);
	
	public List<Usuario> findByPapelLike(Papel papel);
	
}
