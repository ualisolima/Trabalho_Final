package br.ufc.trabalho_final.model;

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


@Entity (name = "usuario")
public class Usuario {
	
	@Id
	@Column (nullable = false)
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id_usuario ;
	
	@Column (nullable = false)
	private String login ;
	
	@Column (nullable = false)
	private String senha ;
	
	@Column (nullable = false)
	private String nome ;
	
	@Column (nullable = false)
	private String email ;
	
	@OneToMany(mappedBy="usuario",
			   targetEntity=Noticia.class,
			   fetch=FetchType.LAZY,
			   cascade = CascadeType.ALL)
	private List<Noticia> noticias;

	@OneToMany(mappedBy="usuario",
			   targetEntity=Comentario.class,
			   fetch=FetchType.LAZY,
			   cascade = CascadeType.ALL)
	private List<Comentario> comentarios ;
	
	@Column(insertable=false,
			updatable=false,
			nullable=false)
	private Long id_papel ;
	
	@ManyToOne
	@JoinColumn(name="id_papel", referencedColumnName="id_papel")
	private Papel papel ;
	
	@OneToMany(mappedBy="usuario",
				targetEntity = Classificado.class,
				fetch = FetchType.LAZY)
	private List<Classificado> classificados;
	
	
	public List<Classificado> getClassificados() {
		return classificados;
	}

	public void setClassificados(List<Classificado> classificados) {
		this.classificados = classificados;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public Long getId_papel() {
		return id_papel;
	}

	public void setId_papel(Long id_papel) {
		this.id_papel = id_papel;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	

}
