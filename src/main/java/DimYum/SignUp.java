package DimYum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// class, methods, inheritance
public class SignUp extends AuthForm {
    // variabel
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
//variabel statis
    public static String registeredEmail = "";
    public static String registeredPassword = "";
//constructor
    public SignUp() {
        super("Sign Up - DimYum");
        initializeComponents();
    }
//methods
    private void initializeComponents() {
        // Logo Image (FIXED PATH)
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

        // Title - Centered
        JLabel titleLabel = createLabel("Buat Akun DimYum Kamu", titleFont, false);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Subtitle - Centered
        JLabel subtitleLabel = new JLabel(
                "<html><center><font color='white'>Mulai petualangan kulinermu langsung dari meja makan pesanan cepat, tanpa ribet, tinggal klik!</font></center></html>",
                SwingConstants.CENTER
        );
        subtitleLabel.setFont(textFont);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(subtitleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Email Field - Centered
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBackground(panel.getBackground());
        JLabel emailLabel = createLabel("Email", textFont, false);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailPanel.add(emailLabel);
        emailField = createTextField(20);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailPanel.add(emailField);
        panel.add(emailPanel);

        // Password Field - Centered
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

        // Confirm Password Field - Centered
        JPanel confirmPanel = new JPanel();
        confirmPanel.setLayout(new BoxLayout(confirmPanel, BoxLayout.Y_AXIS));
        confirmPanel.setBackground(panel.getBackground());
        JLabel confirmLabel = createLabel("Konfirmasi Kata Sandi", textFont, false);
        confirmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPanel.add(confirmLabel);
        confirmPasswordField = createPasswordField(25);
        confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPanel.add(confirmPasswordField);
        panel.add(confirmPanel);

        // Sign Up Button - Centered
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        JButton signUpButton = createButton("Daftar Sekarang");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.addActionListener(this::handleSignUp);
        panel.add(signUpButton);

        // Login Link - Centered (now goes to Login page)
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        JLabel loginLabel = new JLabel(
                "<html><center><font color='white'>Sudah punya akun? </font><font color='#FFAC09'>Masuk</font></center></html>",
                SwingConstants.CENTER
        );
        loginLabel.setFont(textFont);
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //polymorpshism
        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Login().setVisible(true);
            }
        });
        panel.add(loginLabel);
    }
//methods
    private void handleSignUp(ActionEvent e) {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.matches("^[^@]+@[^@]+[^@]+$")) {
            JOptionPane.showMessageDialog(frame,
                    "Email harus valid dan mengandung '@' serta domain!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(frame,
                    "Kata sandi harus minimal 8 karakter!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            JOptionPane.showMessageDialog(frame,
                    "Kata sandi harus mengandung:\n- Huruf besar\n- Huruf kecil\n- Angka",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(frame,
                    "Kata sandi tidak cocok!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        registeredEmail = email;
        registeredPassword = password;

        JOptionPane.showMessageDialog(frame,
                "Pendaftaran berhasil!\nEmail: " + email,
                "Success", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
        //object
        new Login().setVisible(true);
    }
}