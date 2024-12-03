<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario de Anuncio</title>
    <!-- Estilos de Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        
        <h1 class="mb-4">${anuncio != null ? "Editar Anuncio" : "Nuevo Anuncio"}</h1>
        
        <form action="anuncios?accion=${anuncio != null ? 'actualizar' : 'insertar'}" method="post">
            
            <input type="hidden" name="id" value="${anuncio != null ? anuncio.id : ''}">
            
            <div class="mb-3">
                <label for="titulo" class="form-label">Título:</label>
                <input type="text" id="titulo" name="titulo" class="form-control" value="${anuncio != null ? anuncio.titulo : ''}" required>
            </div>

            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción:</label>
                <textarea id="descripcion" name="descripcion" class="form-control" required>${anuncio != null ? anuncio.descripcion : ''}</textarea>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">${anuncio != null ? "Actualizar" : "Guardar"}</button>
                <a href="anuncios" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
                
    </div>
</body>
</html>


