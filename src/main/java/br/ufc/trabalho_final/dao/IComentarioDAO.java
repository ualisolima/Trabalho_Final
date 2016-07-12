package br.ufc.trabalho_final.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.trabalho_final.model.Comentario;

public interface IComentarioDAO extends JpaRepository<Comentario, Long>, IComentarioEnhaced{

}
