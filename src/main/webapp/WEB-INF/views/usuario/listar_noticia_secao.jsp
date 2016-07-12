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
			<div class="dropdown">
				<button class="dropbtn">Meu Cadastro</button>
				<div class="dropdown-content">
					<a href="alterarUsuarioFormulario?id_usuario=${usuario_logado.id_usuario}">Alterar meu cadastro</a>
				</div>
			</div>
			<button class="dropbtn"><a href="efetuarLogout">Logout</a></button>
    	</c:otherwise>
	</c:choose>
<h2>Listar Noticias</h2>
	<c:choose>
		<c:when test="${not empty mensagem}">
			<script>alert('${mensagem}');</script>
		</c:when>
	</c:choose>
	<table>
	<c:forEach var="n" items="${noticias}">
			<c:choose>
				<c:when test="${not empty usuario_logado}">
					<c:choose>
						<c:when test="${n.id_usuario eq usuario_logado.id_usuario or usuario_logado.id_papel eq 1}">
							<tr><button class="dropbtn"><a href="apagarNoticia?id_noticia=${n.id_noticia}">APAGAR</a></button></tr>
							<tr><button class="dropbtn"><a href="alterarNoticiaFormulario?id_noticia=${n.id_noticia}">ALTERAR</a></button></tr>
						</c:when>
					</c:choose>
				</c:when>
			</c:choose>
			<tr><h1><a href="visualizarNoticia?id_noticia=${n.id_noticia}">${n.titulo}</a></h1></tr>
			<tr><h3><a href="visualizarNoticia?id_noticia=${n.id_noticia}">${n.subtitulo}</a></h3></tr>
			<tr>${n.data_noticia} , notícia por ${n.usuario.nome}<br /></tr>
			
	</c:forEach>
	</table>	

</body>
</html>