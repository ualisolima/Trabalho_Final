package br.ufc.trabalho_final.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.trabalho_final.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> , IUsuarioEnhaced {

}
