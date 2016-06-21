package br.ufc.trabalho_final.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.trabalho_final.model.Secao;

public interface ISecaoDAO extends JpaRepository<Secao, Long>, ISecaoEnhaced {

}
