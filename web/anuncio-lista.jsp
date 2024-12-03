<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Anuncios</title>
    <!-- Estilos de Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <h1>Lista de Anuncios</h1>
        <a href="anuncios?accion=nuevo" class="btn btn-success mb-3">Crear Nuevo Anuncio</a>
        
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Descripción</th>
                    <th>Fecha de Publicación</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="anuncio" items="${listaAnuncios}">
                    <tr>
                        <td>${anuncio.id}</td>
                        <td>${anuncio.titulo}</td>
                        <td>${anuncio.descripcion}</td>
                        <td>${anuncio.fechaPublicacion}</td>
                        <td>
                            <a href="anuncios?accion=editar&id=${anuncio.id}" class="btn btn-warning btn-sm">Editar</a>
                            <a href="anuncios?accion=eliminar&id=${anuncio.id}" onclick="return confirm('¿Seguro que quieres eliminar este anuncio?')" class="btn btn-danger btn-sm">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>


