package br.ufc.trabalho_final.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.trabalho_final.model.Papel;

public interface IPapelDAO extends JpaRepository<Papel, Long>, IPapelEnhaced{

}
