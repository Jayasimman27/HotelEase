package Hotel.Managment.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField textField1;
    JPasswordField passwordField1;
    JButton b1, b2;

    // Generic AuthenticationResponse class to wrap the login result
    private static class AuthenticationResponse<T> {
        private final boolean success;
        private final T data;

        public AuthenticationResponse(boolean success, T data) {
            this.success = success;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public T getData() {
            return data;
        }
    }

    public Login() {
        // UI setup
        JLabel label1 = new JLabel("Username");
        label1.setBounds(40, 20, 100, 30);
        label1.setFont(new Font("Tahoma", Font.BOLD, 16));
        label1.setForeground(Color.WHITE);
        add(label1);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(40, 70, 100, 30);
        label2.setFont(new Font("Tahoma", Font.BOLD, 16));
        label2.setForeground(Color.WHITE);
        add(label2);

        textField1 = new JTextField();
        textField1.setBounds(150, 20, 150, 30);
        textField1.setForeground(Color.WHITE);
        textField1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField1.setBackground(new Color(26, 104, 110));
        add(textField1);

        passwordField1 = new JPasswordField();
        passwordField1.setBounds(150, 70, 150, 30);
        passwordField1.setForeground(Color.WHITE);
        passwordField1.setBackground(new Color(26, 104, 110));
        add(passwordField1);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/rec.gif"));
        Image i1 = imageIcon.getImage().getScaledInstance(255, 300, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(318, -30, 255, 300);
        add(label);

        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("Tahoma", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("TAHOMA", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(new Color(3, 45, 48));
        setLayout(null);
        setLocation(400, 270);
        setSize(600, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            AuthenticationResponse<String> response = authenticateUser(textField1.getText(), new String(passwordField1.getPassword()));
            if (response.isSuccess()) {
                new Dashboard();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, response.getData());
            }
        } else {
            System.exit(0);
        }
    }

    private AuthenticationResponse<String> authenticateUser(String user, String pass) {
        try {
            con c = new con();  // using the existing 'con' object for database connection
            String query = "SELECT * FROM login WHERE username = '"+user+"' AND password = '"+pass+"'";


            ResultSet resultSet = c.statement.executeQuery(query);
            if (resultSet.next()) {
                resultSet.close();
                return new AuthenticationResponse<>(true, "Login successful!");
            } else {
                return new AuthenticationResponse<>(false, "Invalid credentials.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AuthenticationResponse<>(false, "An error occurred.");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
