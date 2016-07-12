<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    right: 0;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
    background-color: #3e8e41;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${noticia.titulo}</title>
</head>
<body>
<button class="dropbtn"><a href="/">Home</a></button>
	<c:forEach var="s" items="${secoes}">
		<button class="dropbtn"><a href="listarNoticiaSecao?id_secao=${s.id_secao}">${s.titulo}</a></button>
	</c:forEach>
	<button class="dropbtn"><a href="listarClassificado">Classificados</a></button>
	<c:choose>
    	<c:when test="${empty usuario_logado}">
        	<button class="dropbtn"><a href="efetuarLoginFormulario">Efetuar Login</a></button>
        	<button class="dropbtn"><a href="inserirUsuarioFormulario">Cadastre-se</a></button>
    	</c:when>    
    	<c:otherwise>
        	<c:choose>
		    	<c:when test="${usuario_logado.id_papel eq 1}">
		        	<div class="dropdown">
					  <button class="dropbtn">Menu Administrador</button>
					  <div class="dropdown-content">
					  	<a href="inserirSecaoFormulario">Cadastrar Seção</a>
					    <a href="inserirJornalistaFormulario">Inserir Jornalista</a>
					    <a href="listarNoticiaAdm">Listar Notícias</a>
					    <a href="menuAdministrador">Mostrar Menu Completo</a>
					  </div>
					</div>
		    	</c:when>
		    	<c:when test="${usuario_logado.id_papel eq 2}">
		        	<div class="dropdown">
					  <button class="dropbtn">Menu Jornalista</button>
					  <div class="dropdown-content">
					    <a href="inserirNoticiaFormulario">Cadastrar Notícia</a>
					    <a href="listarNoticia">Listar Noticias</a>
					    <a href="menuJornalista">Mostrar Menu Completo</a>
					  </div>
					</div>
		    	</c:when>
			</c:choose>
			<div class="dropdown">
				<button class="dropbtn">Meu Cadastro</button>
				<div class="dropdown-content">
					<a href="alterarUsuarioFormulario?id_usuario=${usuario_logado.id_usuario}">Alterar meu cadastro</a>
				</div>
			</div>
			<button class="dropbtn"><a href="efetuarLogout">Logout</a></button>
    	</c:otherwise>
	</c:choose>
<h1>${noticia.titulo}</h1>
	<c:choose>
		<c:when test="${not empty mensagem}">
			<script>alert('${mensagem}');</script>
		</c:when>
	</c:choose>
	<table>
		<tr>
			<td><h3>${noticia.subtitulo}</h3></td>
		</tr>
		<c:choose>
			<c:when test="${not empty usuario_logado}">
				<c:choose>
					<c:when test="${usuario_logado.id_papel eq 1 or usuario_logado.id_usuario eq noticia.usuario.id_usuario }">
						<tr><button class="dropbtn"><a href="apagarNoticia?id_noticia=${noticia.id_noticia}">APAGAR</a></button></tr>
						<tr><button class="dropbtn"><a href="alterarNoticiaFormulario?id_noticia=${noticia.id_noticia}">ALTERAR</a></button></tr>	
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>
		<tr>
			<c:url var="post_url"  value="/resources/images/noticia/${noticia.id_noticia}.jpg" />
			<td><img src="${post_url}" /></td>
		</tr>
		<tr>
			<td><h4>${noticia.texto}</h4></td>
		</tr>
		<tr>
			<td>
			<c:url var="post_author"  value="/resources/images/usuario/${noticia.usuario.id_usuario}.jpg" />
			<img src="${post_author}" width="75" height="75" />
			<h6>Por : ${noticia.usuario.nome}, ${noticia.data_noticia }</h6>
			</td>
		</tr>
		<tr>
			<td>Comentários</td>
		</tr>
		</table>
		<table>
		<c:choose>
			<c:when test="${not empty usuario_logado}">
				<c:choose>
					<c:when test="${usuario_logado.id_papel eq 3}">
						<form action="inserirComentario" method="post">
							<input type="hidden" value="${usuario_logado.id_usuario}" name="id_usuario" />
							<input type="hidden" value="${noticia.id_noticia}" name="id_noticia" />
							<textarea rows="3" cols="50" name="texto"></textarea>
							<button type="submit" class="dropbtn">Postar</button>
						</form>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>
		<c:forEach var="c" items="${comentarios}">
			<tr>
				<td>
					<c:url var="post_coment"  value="/resources/images/usuario/${c.usuario.id_usuario}.jpg" />
					<img src="${post_coment}" width="65" height="65" />
					${c.usuario.nome} disse: <br />
					${c.texto}
				</td>
			</tr>
			<c:choose>
				<c:when test="${c.id_usuario eq usuario_logado.id_usuario}">
					<tr>
						<td><button class="dropbtn"><a href="apagarComentario?id_comentario=${c.id_comentario}">Apagar</a></button></td>
					</tr>
				</c:when>
			</c:choose>
		</c:forEach>	
		</table>
</body>
</html>