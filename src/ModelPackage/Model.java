/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelPackage;

/**
 *
 * @author HP
 */
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;


public class Model extends AbstractTableModel{
    // List untuk menyimpan data model
    List<ModelData> md;
    
    // Konstruktor untuk inisialisasi dengan data model
    public Model(List<ModelData> md){
    this.md = md;
    }
    
    // Mengembalikan jumlah baris dalam tabel (jumlah data dalam list)
    @Override
    public int getRowCount() {
        return md.size();
    }

     // Mengembalikan jumlah kolom dalam tabel
    @Override
    public int getColumnCount() {
        return 5;
    }
    
    // Mengembalikan nama kolom berdasarkan indeksnya
    @Override
    public String getColumnName (int column){
        switch(column){
            case 0:
                return "Judul";
            case 1:
                return "Penulis";
            case 2:
                return "Rating";
            case 3:
                return "Harga";
            default:
                return null;
        }
    }

    // Mengembalikan nilai di sel tertentu berdasarkan baris dan kolom
    @Override
    public Object getValueAt(int row, int column) {
        
        switch(column){
            case 0:
                return md.get(row).getJudul();
            case 1:
                return md.get(row).getPenulis();
            case 2:
                return md.get(row).getRating();
            case 3:
                return md.get(row).getHarga();
            default:
                return null;
        }
    }
    
    
}

