package br.ufc.trabalho_final.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name = "comentario")
public class Comentario {

	@Id
	@Column (nullable = false)
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id_comentario ;
	
	@Column(insertable=false,
			updatable=false,
			nullable=false)
	private Long id_noticia ;
	
	@ManyToOne
	@JoinColumn(name="id_noticia", referencedColumnName="id_noticia")
	private Noticia noticia ;
	
	@Column (insertable = false,
			updatable = false,
			nullable = false)
	private Long id_usuario ;
	
	@ManyToOne
	@JoinColumn (name="id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario ;
	
	@Column (nullable = false)
	private String texto ;

	public Long getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(Long id_comentario) {
		this.id_comentario = id_comentario;
	}

	public Long getId_noticia() {
		return id_noticia;
	}

	public void setId_noticia(Long id_noticia) {
		this.id_noticia = id_noticia;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	
}
