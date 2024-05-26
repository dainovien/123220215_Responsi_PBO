/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

/**
 *
 * @author HP
 */
import Controller.ControllerPerpustakaan;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        ControllerPerpustakaan controller = new ControllerPerpustakaan(view);
        controller.showTabel();
        view.setVisible(true);
    }
}

