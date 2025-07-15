package DimYum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// class, inheritance , access modifiers
public class Splash extends JFrame {
    //methods, constructor
    public Splash() {
        // Set up the frame
        setTitle("Splash");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Color definitions , variabel
        Color backgroundColor = new Color(0x7E, 0x08, 0x08);
        Color buttonColor = new Color(0xFF, 0xAC, 0x09);
        Color textColor = Color.WHITE;

        // Main content panel with GridBagLayout for perfect centering
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        contentPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        // Title label, variabel
        JLabel titleLabel = new JLabel("Selamat Datang di DimYum");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(textColor);
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(titleLabel, gbc);

        // Description text - perfectly centered
        JLabel description = new JLabel(
                "<html><center><font color='white'>Makin hemat dan praktis pesan makanan dan minuman favorit, banyak pilihan menu, promo menarik, tinggal klik tanpa ribet!</font></center></html>"
        );
        description.setFont(new Font("Arial", Font.PLAIN, 12));
        description.setHorizontalAlignment(SwingConstants.CENTER);
        description.setPreferredSize(new Dimension(300, 60));
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(description, gbc);

        // Image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(backgroundColor);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        try {
            ImageIcon originalIcon = new ImageIcon("logoDimYum.jpg");
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            JLabel imageLabel = new JLabel("[Logo]");
            imageLabel.setForeground(textColor);
            imagePanel.add(imageLabel);
            System.out.println("Error loading image: " + e.getMessage());
        }
        gbc.insets = new Insets(0, 0, 30, 0);
        contentPanel.add(imagePanel, gbc);

        // Register button - smaller size
        JButton registerButton = new JButton("Daftar akun baru");
        registerButton.setPreferredSize(new Dimension(200, 35)); // Smaller size
        registerButton.setMinimumSize(new Dimension(200, 35));
        registerButton.setBackground(buttonColor);
        registerButton.setForeground(Color.BLACK);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        //polymorphism
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignUp().setVisible(true);
            }
        });
        gbc.insets = new Insets(0, 0, 15, 0);
        contentPanel.add(registerButton, gbc);

        // Login label - perfectly centered with colored text
        JLabel loginLabel = new JLabel(
                "<html><center><font color='white'>Udah daftar? </font><font color='#FFAC09'>Masuk di sini</font></center></html>"
        );
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
                new Login().setVisible(true);
            }
        });
        gbc.insets = new Insets(10, 0, 0, 0);
        contentPanel.add(loginLabel, gbc);

        add(contentPanel, BorderLayout.CENTER);
        getContentPane().setBackground(backgroundColor);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //object
            Splash splash = new Splash();
            splash.setVisible(true);
        });
    }
}
