package br.ufc.trabalho_final.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name = "classificado")
public class Classificado {
	
	@Id
	@Column(nullable = false)
	private Long id_classificado ;
	
	@Column (nullable = false)
	private String titulo ;
	
	@Column (nullable = false)
	private String texto ;
	
	@Column (nullable = false)
	private Double preco ;
	
	@Column (nullable = false)
	private String telefone ;
	
	@Column
	private Double melhor_oferta ;
	
	@Column (nullable = false)
	private Timestamp data_oferta ;
	
	@Column (insertable = false, updatable = false)
	private Long id_usuario ;
	
	@ManyToOne
	@JoinColumn (name= "id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario ;

	public Long getId_classificado() {
		return id_classificado;
	}

	public void setId_classificado(Long id_classificado) {
		this.id_classificado = id_classificado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getMelhor_oferta() {
		return melhor_oferta;
	}

	public void setMelhor_oferta(Double melhor_oferta) {
		this.melhor_oferta = melhor_oferta;
	}

	public Timestamp getData_oferta() {
		return data_oferta;
	}

	public void setData_oferta(Timestamp data_oferta) {
		this.data_oferta = data_oferta;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
