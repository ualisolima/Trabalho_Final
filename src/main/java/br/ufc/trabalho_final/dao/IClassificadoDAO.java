package br.ufc.trabalho_final.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.trabalho_final.model.Classificado;

public interface IClassificadoDAO extends JpaRepository<Classificado, Long>, IClassificadoEnhaced{

}
