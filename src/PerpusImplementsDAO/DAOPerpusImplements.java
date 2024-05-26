/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PerpusImplementsDAO;

/**
 *
 * @author HP
 */
import PerpustakaanDAO.DAOPerpustakaan;
import ModelPackage.ModelData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPerpusImplements implements DAOPerpustakaan {
    // URL, username, dan password untuk koneksi ke database MySQL
    private final String URL = "jdbc:mysql://localhost:3306/perpustakaan";
    private final String USER = "root";
    private final String PASSWORD = "";

    // Metode untuk membuat koneksi ke database
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Implementasi metode untuk mendapatkan semua data dari tabel buku
    @Override
    public List<ModelData> getALL() {
        List<ModelData> list = new ArrayList<>();
        String sql = "SELECT * FROM buku";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ModelData md = new ModelData();
                md.setId(rs.getInt("id"));
                md.setJudul(rs.getString("judul"));
                md.setPenulis(rs.getString("penulis"));
                md.setRating(rs.getFloat("rating"));
                md.setHarga(rs.getInt("harga"));
                list.add(md);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }

     // Implementasi metode untuk menambahkan data baru ke tabel buku
    @Override
    public void insert(ModelData data) {
        String sql = "INSERT INTO buku (judul, penulis, rating, harga) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, data.getJudul());
            pstmt.setString(2, data.getPenulis());
            pstmt.setFloat(3, data.getRating());
            pstmt.setInt(4, data.getHarga());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementasi metode untuk memperbarui data yang ada di tabel buku
    @Override
    public void update(ModelData data) {
        String sql = "UPDATE buku SET judul = ?, penulis = ?, rating = ?, harga = ? WHERE id = ?";
        
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, data.getJudul());
            pstmt.setString(2, data.getPenulis());
            pstmt.setFloat(3, data.getRating());
            pstmt.setInt(4, data.getHarga());
            pstmt.setInt(5, data.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementasi metode untuk menghapus data dari tabel buku berdasarkan ID
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM buku WHERE id = ?";
        
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

