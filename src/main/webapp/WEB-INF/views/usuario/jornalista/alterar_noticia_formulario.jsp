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
<title>Insert title here</title>
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
			<button class="dropbtn"><a href="efetuarLogout">Logout</a></button>
			<div class="dropdown">
				<button class="dropbtn">Meu Cadastro</button>
				<div class="dropdown-content">
					<a href="alterarUsuarioFormulario?id_usuario=${usuario_logado.id_usuario}">Alterar meu cadastro</a>
				</div>
			</div>
    	</c:otherwise>
	</c:choose>
	<h2>Inserir Notícia</h2> 
	<form action="alterarNoticia" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Seção: </td>
				<td>
					<input type="hidden" name="id_usuario" value="${usuario_logado.id_usuario}" />
					<input type="hidden" name="id_noticia" value="${noticia.id_noticia}" />
					<select name="id_secao" value="${noticia.titulo}" >
						<c:forEach var="s" items="${secoes}">
							<option value="${s.id_secao}">
								${s.titulo}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Título da Notícia: </td>
				<td> <input type="text" value="${noticia.titulo }" name="titulo" /></td>
			</tr>
			<tr>
				<td>Subtítulo da Notícia: </td>
				<td> <input type="text" value="${noticia.subtitulo}" name="subtitulo" /></td>
			</tr>
			<tr>
				<td>Descreva aqui a sua notícia: </td>
				<td> <textarea name="texto" cols="100" rows="15">${noticia.texto}</textarea></td>
			</tr>
			<tr>
				<c:url var="post_url"  value="/resources/images/noticia/${noticia.id_noticia}.jpg" />
				<td><input type="file" class="dropbtn" name="image" value="${post_url}" /></td>
			</tr>
			<tr>
				<td><input type="submit" class="dropbtn" value="SALVAR" /></td>
			</tr>
		</table>
	</form>
</body></html>