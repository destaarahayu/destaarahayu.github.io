package DimYum;

import javax.swing.*;
import java.awt.*;
//class, abstarct
public abstract class AuthForm {
    //variabel
    protected JFrame frame;
    protected JPanel panel;
    protected Color primaryColor = new Color(0xFF, 0xAC, 0x09);
    protected Color backgroundColor = new Color(0x7E, 0x08, 0x08);
    protected Color textColor = Color.WHITE;
    protected Font titleFont = new Font("Arial", Font.BOLD, 24);
    protected Font textFont = new Font("Arial", Font.PLAIN, 14);
//contructor
    public AuthForm(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 650);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        panel.setBackground(backgroundColor);

        frame.add(panel);
    }
//method
    protected JLabel createLabel(String text, Font font, boolean alignLeft) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(textColor);
        label.setAlignmentX(alignLeft ? Component.LEFT_ALIGNMENT : Component.CENTER_ALIGNMENT);
        return label;
    }
//method
    protected JTextField createTextField(int width) {
        JTextField field = new JTextField(width);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBackground(new Color(255, 255, 255, 200));
        return field;
    }
//method
    protected JPasswordField createPasswordField(int i) {
        JPasswordField field = new JPasswordField(25);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBackground(new Color(255, 255, 255, 200));
        return field;
    }
//method
    protected JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(primaryColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }
//method
    protected void addFieldWithLabel(JPanel panel, String labelText, Component field) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(backgroundColor);
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label = createLabel(labelText, textFont, true);
        fieldPanel.add(label);
        fieldPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        fieldPanel.add(field);

        panel.add(fieldPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }
//method
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}