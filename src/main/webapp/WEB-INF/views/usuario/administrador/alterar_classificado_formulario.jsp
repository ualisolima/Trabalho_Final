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
<title>Inserir Notícia</title>
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
	<h2>Inserir Classificado</h2> 
	<form action="alterarClassificado" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Título do Classificado: </td>
				<td>
					<input type="hidden" name="id_classificado" value="${classificado.id_classificado}" />
					<input type="text" name="titulo" value="${classificado.titulo}" />
				</td>
			</tr>
			<tr>
				<td>Descreva aqui o classificado: </td>
				<td> <textarea name="texto" cols="100" rows="15">${classificado.texto}</textarea></td>
			</tr>
			<tr>
				<td>Preço inicial:</td>
				<td><input type="number" step="0.01" min=0 name="preco" value="${classificado.preco}" /></td>
			</tr>
			<tr>
				<td>Telfone para contato: </td>
				<td> <input type="text" name="telefone" value="${classificado.telefone}" /></td>
			</tr>
			<tr>
				<c:url var="post_url"  value="/resources/images/classificado/${c.id_classificado}.jpg" />
				<td><input type="file" class="dropbtn" name="image" value="${post_url}" /></td>
			</tr>
			<tr>
				<td><input type="submit" class="dropbtn" value="SALVAR" /></td>
			</tr>
		</table>
	</form>
</body>
</html>