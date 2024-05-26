/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PerpustakaanDAO;

/**
 *
 * @author HP
 */
import ModelPackage.ModelData;
import java.util.List;

public interface DAOPerpustakaan {
    // Mengambil semua data buku dari database
    List<ModelData> getALL();
    void insert(ModelData data);
    void update(ModelData data);
    void delete(int id);
}
