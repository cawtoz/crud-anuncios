package servlet;

import dao.AnuncioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Anuncio;


import java.io.IOException;
import java.util.List;

@WebServlet("/anuncios")
public class AnuncioServlet extends HttpServlet {

    
    private final AnuncioDAO anuncioDAO = new AnuncioDAO();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion == null) {
            listarAnuncios(request, response);
            return;
        }

        switch (accion) {
            case "nuevo" -> mostrarFormularioNuevo(request, response);
            case "editar" -> mostrarFormularioEditar(request, response);
            case "eliminar" -> eliminarAnuncio(request, response);
            default -> response.sendRedirect("anuncios");
        }
     
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "insertar" -> insertarAnuncio(request, response);
            case "actualizar" -> actualizarAnuncio(request, response);
            default -> response.sendRedirect("anuncios");
        }
        
    }


    private void listarAnuncios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Anuncio> listaAnuncios = anuncioDAO.listarAnuncios();
        
        request.setAttribute("listaAnuncios", listaAnuncios);
        request.getRequestDispatcher("anuncio-lista.jsp").forward(request, response);
        
    }
    

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("anuncio-formulario.jsp").forward(request, response);
    }

    
    private void insertarAnuncio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        
        Anuncio anuncio = new Anuncio(titulo, descripcion);
        anuncioDAO.insertarAnuncio(anuncio);
        
        response.sendRedirect("anuncios");
        
    }
    

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Anuncio anuncio = anuncioDAO.obtenerAnuncioPorId(id);
        
        request.setAttribute("anuncio", anuncio);
        request.getRequestDispatcher("anuncio-formulario.jsp").forward(request, response);
        
    }
    

    private void actualizarAnuncio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        
        Anuncio anuncio = anuncioDAO.obtenerAnuncioPorId(id);
        anuncio.setTitulo(titulo);
        anuncio.setDescripcion(descripcion);
        anuncioDAO.actualizarAnuncio(anuncio);
        
        response.sendRedirect("anuncios");
        
    }

    
    private void eliminarAnuncio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        anuncioDAO.eliminarAnuncio(id);
        
        response.sendRedirect("anuncios");
        
    }
    
}