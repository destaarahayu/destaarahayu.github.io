package DimYum;

import DimYum.View.MenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Tampilkan splash screen pertama kali
            Splash splash = new Splash();
            splash.setVisible(true);

            // Set timer untuk menutup splash screen setelah beberapa detik
            Timer timer = new Timer(3000, e -> {
                splash.dispose();
                showLoginForm(); // Ubah ini dari showMainMenu() ke showLoginForm()
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    private static void showLoginForm() {
        Login loginForm = new Login();
        loginForm.setVisible(true);
    }

    // Tambahkan method untuk menampilkan main menu yang bisa dipanggil setelah login berhasil
    public static void showMainMenu() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Menu DimYum");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(850, 720);
            frame.setLocationRelativeTo(null);
            frame.add(new MenuGUI());
            frame.setVisible(true);
        });
    }
}
