package dao;

import database.DatabaseConnection;
import model.Anuncio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnuncioDAO {
    
    // Obtener conexion a la conexión
    private final Connection conexion = DatabaseConnection.getConnection();

    // Método para insertar un nuevo anuncio
    public void insertarAnuncio(Anuncio anuncio) {
        String sql = "INSERT INTO anuncios (titulo, descripcion) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, anuncio.getTitulo());
            ps.setString(2, anuncio.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el anuncio", e);
        }
    }

    // Método para listar todos los anuncios
    public List<Anuncio> listarAnuncios() {
        List<Anuncio> listaAnuncios = new ArrayList<>();
        String sql = "SELECT * FROM anuncios";
        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Anuncio anuncio = new Anuncio(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getTimestamp("fecha_publicacion").toLocalDateTime()
                );
                listaAnuncios.add(anuncio);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los anuncios", e);
        }
        return listaAnuncios;
    }

    // Método para obtener un anuncio por su ID
    public Anuncio obtenerAnuncioPorId(int id) {
        String sql = "SELECT * FROM anuncios WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Anuncio(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getTimestamp("fecha_publicacion").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el anuncio por ID", e);
        }
        return null;
    }

    // Método para actualizar un anuncio
    public void actualizarAnuncio(Anuncio anuncio) {
        String sql = "UPDATE anuncios SET titulo = ?, descripcion = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, anuncio.getTitulo());
            ps.setString(2, anuncio.getDescripcion());
            ps.setInt(3, anuncio.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el anuncio", e);
        }
    }

    // Método para eliminar un anuncio
    public void eliminarAnuncio(int id) {
        String sql = "DELETE FROM anuncios WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el anuncio", e);
        }
    }
    
}
