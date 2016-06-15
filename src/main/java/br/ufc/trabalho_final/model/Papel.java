package br.ufc.trabalho_final.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity (name = "papel")
public class Papel {
	
	@Id
	@Column (nullable = false)
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id_papel ;
	
	@Column (nullable = false)
	private String papel ;
	
	@OneToMany(mappedBy="papel",
			   targetEntity=Usuario.class,
			   fetch=FetchType.LAZY)
	private List<Usuario> usuarios;
	
	public Long getId_papel() {
		return id_papel;
	}

	public void setId_papel(Long id_papel) {
		this.id_papel = id_papel;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
