package DimYum;

import DimYum.View.MenuGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// class, methods, inheritance
public class Login extends AuthForm {
    // variabel
    private JTextField emailField;
    private JPasswordField passwordField;
    //constructor
    public Login() {
        super("Login - DimYum");
        initializeComponents();
    }
    //methods
    private void initializeComponents() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Logo
        try {
            ImageIcon originalIcon = new ImageIcon("logoDimYum.jpg");
            Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(logoLabel);
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }

        // Title
        JLabel titleLabel = createLabel("Masuk ke DimYum", titleFont, false);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Subtitle
        JLabel subtitleLabel = new JLabel(
                "<html><center><font color='white'>Yuk, lanjut jelajahi menu enak dan pesan langsung dari tempat dudukmu</font><center></html>",
                SwingConstants.CENTER
        );
        subtitleLabel.setFont(textFont);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(subtitleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Email Field
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBackground(panel.getBackground());
        JLabel emailLabel = createLabel("Email", textFont, false);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailPanel.add(emailLabel);
        emailField = createTextField(25);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailPanel.add(emailField);
        panel.add(emailPanel);

        // Password Field
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setBackground(panel.getBackground());
        JLabel passwordLabel = createLabel("Kata Sandi", textFont, false);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.add(passwordLabel);
        passwordField = createPasswordField(25);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.add(passwordField);
        panel.add(passwordPanel);

        // Login Button
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        JButton loginButton = createButton("Masuk");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(this::handleLogin);
        panel.add(loginButton);

        // Sign Up Link
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        JLabel signupLabel = new JLabel(
                "<html><center><font color='white'>Belum punya akun? <font color='#FFAC09'>Daftar</font></center></html>",
                SwingConstants.CENTER
        );
        signupLabel.setFont(textFont);
        signupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //polymorphism
        signupLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new SignUp().setVisible(true);
            }
        });
        panel.add(signupLabel);
    }
//methods
    private void handleLogin(ActionEvent e) {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Email dan kata sandi harus diisi!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.equals(SignUp.registeredEmail) || !password.equals(SignUp.registeredPassword)) {
            JOptionPane.showMessageDialog(frame,
                    "Email atau kata sandi salah!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(frame,
                "Login berhasil!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();

        SwingUtilities.invokeLater(() -> {
            MenuGUI menuGUI = new MenuGUI();
            menuGUI.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}