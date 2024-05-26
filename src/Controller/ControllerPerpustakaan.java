/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author HP
 */
import java.util.List;
import PerpustakaanDAO.DAOPerpustakaan;
import PerpusImplementsDAO.DAOPerpusImplements;
import javax.swing.JOptionPane;
import ModelPackage.ModelData;
import Main.View;
import javax.swing.table.DefaultTableModel;

public class ControllerPerpustakaan {
    private View frame;
    private DAOPerpustakaan implperpus;
    private List<ModelData> md;

    private boolean inputgagal = false;
    

    // Metode untuk memeriksa apakah input dari user valid (tidak kosong)
    private boolean CekInput() {
        return !frame.getInjudul().getText().isEmpty()
                && !frame.getInpenulis().getText().isEmpty()
                && !frame.getInrating().getText().isEmpty()
                && !frame.getInharga().getText().isEmpty();
    }

    // Konstruktor untuk inisialisasi objek ControllerPerpustakaan
    public ControllerPerpustakaan(View frame) {
        this.frame = frame;
        implperpus = new DAOPerpusImplements(); // Corrected instantiation
        md = implperpus.getALL();
    }

    // Metode untuk menampilkan data pada tabel di GUI
    public void showTabel() {
    md = implperpus.getALL();
    String[] columnNames = {"ID", "Judul", "Penulis", "Rating", "Harga", "Total"}; // Add "Total" column
    Object[][] data = new Object[md.size()][6]; // Increase column count to 6

    for (int i = 0; i < md.size(); i++) {
        int k = 500; // Constant value
        int harga = md.get(i).getHarga();
        float rating = md.get(i).getRating();
        int total = harga + k + (int) (rating * 100); // Calculate total

        data[i][0] = md.get(i).getId();
        data[i][1] = md.get(i).getJudul();
        data[i][2] = md.get(i).getPenulis();
        data[i][3] = rating;
        data[i][4] = harga;
        data[i][5] = total; // Add total to data array
    }

    frame.setTabelDataModel(new DefaultTableModel(data, columnNames));
}

    // Metode untuk memasukkan data baru ke database
    public void insert() {
        if (CekInput()) {
            try {
                ModelData md = new ModelData();
                md.setJudul(frame.getInjudul().getText());
                md.setPenulis(frame.getInpenulis().getText());
                md.setRating(Float.parseFloat(frame.getInrating().getText()));
                md.setHarga(Integer.parseInt(frame.getInharga().getText()));

                implperpus.insert(md);
                inputgagal = false;
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan", "Success", JOptionPane.INFORMATION_MESSAGE);
                showTabel(); // Refresh table after insertion
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "Error", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                inputgagal = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

     // Metode untuk memperbarui data yang ada di database
    public void update() {
        int selectedRow = frame.getTabelData().getSelectedRow();
        if (selectedRow != -1 && CekInput()) {
            try {
                int id = (int) frame.getTabelData().getValueAt(selectedRow, 0);
                ModelData md = new ModelData();
                md.setId(id);
                md.setJudul(frame.getInjudul().getText());
                md.setPenulis(frame.getInpenulis().getText());
                md.setRating(Float.parseFloat(frame.getInrating().getText()));
                md.setHarga(Integer.parseInt(frame.getInharga().getText()));

                implperpus.update(md);
                inputgagal = false;
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate", "Success", JOptionPane.INFORMATION_MESSAGE);
                showTabel(); // Refresh table after update
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "Error", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                inputgagal = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris untuk diupdate atau ulangi pengisian data", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Metode untuk menghapus data dari database
    public void delete() {
        int selectedRow = frame.getTabelData().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = (int) frame.getTabelData().getValueAt(selectedRow, 0);
                implperpus.delete(id);
                JOptionPane.showMessageDialog(null, "Data berhasil terhapus", "Success", JOptionPane.INFORMATION_MESSAGE);
                showTabel(); // Refresh table after deletion
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris untuk dihapus", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
}
