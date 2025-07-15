package DimYum;

import DimYum.View.MenuGUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

//constructor
public class MainProfile extends JFrame {

    // enkapsulasi
    private AkunUser user;

    public MainProfile(JFrame parentFrame) {
        this.user = new AkunUser(
                "Jeong Jaehyun",
                "Jaehyun@gmail.com",
                "081234567890",
                "Laki-Laki",
                "01-01-1990",
                "Indonesia",
                "Entrepreneur"
        );

        setTitle("Aplikasi DimYum");
        setSize(600, 720);
        setLocationRelativeTo(parentFrame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color backgroundColor = new Color(0x7E0808);
        Color accentColor = new Color(0xFFAC09);

        // === HEADER ===
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(backgroundColor);

        // Back arrow
        JLabel backLabel = new JLabel("←");
        backLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        backLabel.setForeground(Color.WHITE);
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        backLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                new MenuGUI().setVisible(true);
            }
        });
        headerPanel.add(backLabel, BorderLayout.WEST);

        // Title centered with FlowLayout
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        headerPanel.add(titlePanel, BorderLayout.CENTER);

        // === IMAGE ===
        JPanel imgPanel = new JPanel();
        imgPanel.setBackground(backgroundColor);
        imgPanel.setBorder(new EmptyBorder(10, 0, 20, 0));
        try {
            ImageIcon icon = new ImageIcon("ProfileJaehyun.jpg");
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgPanel.add(imgLabel);
        } catch (Exception e) {
            System.out.println("Gagal memuat gambar profil.");
        }

        // === INFORMASI PENGGUNA ===
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(backgroundColor);
        wrapperPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

        JPanel dataPanel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 30));
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
            }
        };
        dataPanel.setOpaque(false);
        dataPanel.setBorder(new CompoundBorder(
                new EmptyBorder(15, 15, 15, 15),
                new TitledBorder(null, "Informasi Pengguna", TitledBorder.LEFT, TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 14), Color.WHITE)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        Font fieldFont = new Font("Arial", Font.PLAIN, 13);

        JTextField namaField = createTextField(user.getNama(), fieldFont, accentColor);
        JTextField emailField = createTextField(user.getEmail(), fieldFont, accentColor);
        JTextField teleponField = createTextField(user.getNoTelepon(), fieldFont, accentColor);
        JTextField lahirField = createTextField(user.getTanggalLahir(), fieldFont, accentColor);

        JComboBox<String> genderBox = createComboBox(new String[]{"Laki-Laki", "Perempuan"}, user.getJenisKelamin(), fieldFont, accentColor);
        JComboBox<String> negaraBox = createComboBox(new String[]{"Indonesia", "Lainnya"}, user.getKewarganegaraan(), fieldFont, accentColor);
        JComboBox<String> pekerjaanBox = createComboBox(new String[]{
                "Entrepreneur", "Pelajar/Mahasiswa", "Karyawan Swasta",
                "Pegawai Negeri Sipil", "Ibu Rumah Tangga", "Lainnya"
        }, user.getPekerjaan(), fieldFont, accentColor);

        String[] labels = {
                "Nama:", "Email:", "No Telepon:", "Jenis Kelamin:",
                "Tanggal Lahir:", "Kewarganegaraan:", "Pekerjaan:"
        };

        Component[] inputs = {
                namaField, emailField, teleponField, genderBox,
                lahirField, negaraBox, pekerjaanBox
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            JLabel label = new JLabel(labels[i]);
            label.setForeground(Color.WHITE);
            label.setFont(fieldFont);
            dataPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            dataPanel.add(inputs[i], gbc);
        }

        wrapperPanel.add(dataPanel, BorderLayout.CENTER);

        // === BUTTON PANEL ===
        JButton editButton = new JButton("Edit");
        JButton saveButton = new JButton("Save");
        saveButton.setEnabled(false);

        editButton.setBackground(accentColor);
        saveButton.setBackground(accentColor);
        editButton.setForeground(Color.BLACK);
        saveButton.setForeground(Color.BLACK);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);

        editButton.addActionListener(e -> {
            for (Component input : inputs) {
                if (input instanceof JTextField) ((JTextField) input).setEditable(true);
                if (input instanceof JComboBox) ((JComboBox<?>) input).setEnabled(true);
            }
            editButton.setEnabled(false);
            saveButton.setEnabled(true);
        });

        saveButton.addActionListener(e -> {

            // ubah data user bagian dari enkapsulasi
            user.setNama(namaField.getText());
            user.setEmail(emailField.getText());
            user.setNoTelepon(teleponField.getText());
            user.setTanggalLahir(lahirField.getText());
            user.setJenisKelamin((String) genderBox.getSelectedItem());
            user.setKewarganegaraan((String) negaraBox.getSelectedItem());
            user.setPekerjaan((String) pekerjaanBox.getSelectedItem());

            for (Component input : inputs) {
                if (input instanceof JTextField) ((JTextField) input).setEditable(false);
                if (input instanceof JComboBox) ((JComboBox<?>) input).setEnabled(false);
            }
            saveButton.setEnabled(false);
            editButton.setEnabled(true);
        });

        // === ADD TO FRAME ===
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(backgroundColor);
        topPanel.add(headerPanel);
        topPanel.add(imgPanel);

        add(topPanel, BorderLayout.NORTH);
        add(wrapperPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);

        // Restore parent frame
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if (parentFrame != null) {
                    parentFrame.setVisible(true);
                }
            }
        });
    }

    private JTextField createTextField(String text, Font font, Color bg) {
        JTextField tf = new JTextField(text);
        tf.setEditable(false);
        tf.setFont(font);
        tf.setForeground(Color.WHITE);
        tf.setBackground(bg);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.GRAY, 1),
                new EmptyBorder(5, 8, 5, 8)
        ));
        return tf;
    }

    private JComboBox<String> createComboBox(String[] options, String selected, Font font, Color bg) {
        JComboBox<String> cb = new JComboBox<>(options);
        cb.setSelectedItem(selected);
        cb.setEnabled(false);
        cb.setFont(font);
        cb.setForeground(Color.WHITE);
        cb.setBackground(bg);
        cb.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.GRAY, 1),
                new EmptyBorder(2, 8, 2, 8)
        ));
        return cb;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainProfile(null));
    }
}