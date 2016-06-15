package br.ufc.trabalho_final.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity (name = "noticia")
public class Noticia {

	@Id
	@Column (nullable = false)
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id_noticia ;
	
	@Column (nullable = false)
	private String titulo ;
	
	@Column (nullable = false)
	private String subtitulo ;
	
	@Column (nullable = false)
	private String texto ;
	
	@Column(insertable=false,
			updatable=false,
			nullable=false)
	private Long id_usuario ;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
	private Usuario usuario ;
	
	@Column (nullable = false)
	private Timestamp data_noticia ;
	
	@Column(insertable=false,
			updatable=false,
			nullable=false)
	private Long id_secao ;
	
	@ManyToOne
	@JoinColumn(name="id_secao", referencedColumnName="id_secao")
	private Secao secao ;

	@OneToMany(mappedBy="noticia",
			   targetEntity=Comentario.class,
			   fetch=FetchType.LAZY,
			   cascade = CascadeType.REMOVE)
	private List<Comentario> comentarios ;
	
	public Long getId_noticia() {
		return id_noticia;
	}

	public void setId_noticia(Long id_noticia) {
		this.id_noticia = id_noticia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
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

	public Timestamp getData_noticia() {
		return data_noticia;
	}

	public void setData_noticia(Timestamp data_noticia) {
		this.data_noticia = data_noticia;
	}

	public Long getId_secao() {
		return id_secao;
	}

	public void setId_secao(Long id_secao) {
		this.id_secao = id_secao;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
}
