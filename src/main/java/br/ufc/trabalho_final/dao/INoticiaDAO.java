package br.ufc.trabalho_final.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.trabalho_final.model.Noticia;

public interface INoticiaDAO extends JpaRepository<Noticia, Long>, INoticiaEnhaced{
	

}
