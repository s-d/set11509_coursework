package user.view;

import global.GlobalControlCodes;
import user.controller.LoginController;
import user.controller.RegisterController;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class UserView implements Observer {
    JFrame frame;
    private JPanel panel;
    private JLabel labelName;
    private JLabel labelPassword;
    private JTextField fieldName;
    private JPasswordField fieldPassword;
    private JPanel panelButton;
    private JButton buttonLogin;
    private JButton buttonRegister;

    public UserView() {
        frame = new JFrame("UserView");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void hideUserView() {
        frame.dispose();
    }

    public void setLoginControl(LoginController loginControl) {
        System.out.println("UserView: Adding LoginController");
        loginControl = loginControl;
        buttonLogin.addActionListener(loginControl);
    }

    public void setRegisterController(RegisterController registerController) {
        System.out.println("UserView: Adding RegisterController");
        buttonRegister.addActionListener(registerController);
    }

    public String getUserPassword() {
        return String.valueOf(fieldPassword.getPassword());
    }

    public String getUserName() {
        return fieldName.getText();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UserView: " + arg);
        switch ((GlobalControlCodes) arg) {
            case NO_SUCH_USER:
                showInvalidLogin();
                break;
            case INVALID_CREDENTIAL:
                showInvalidCredential();
                break;
            case USER_EXISTS:
                showInvalidRegister();
                break;
            case REGISTERED:
                showValidRegister();
                break;
            case LOG_IN:
                break;
            case FAILED:
                break;
        }
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null
                , message, "Log In"
                , JOptionPane.INFORMATION_MESSAGE);
    }

    private void showInvalidLogin() {
        showMessageDialog("No such User/Password");
    }

    private void showInvalidCredential() {
        showMessageDialog("Invalid credentials");
    }

    private void showInvalidRegister() {
        showMessageDialog("Username already registered");
    }

    private void showValidRegister() {
        showMessageDialog("New user Registered");
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        labelName = new JLabel();
        labelName.setText("User Name");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelName, gbc);
        fieldName = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(fieldName, gbc);
        labelPassword = new JLabel();
        labelPassword.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelPassword, gbc);
        fieldPassword = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(fieldPassword, gbc);
        panelButton = new JPanel();
        panelButton.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(panelButton, gbc);
        buttonLogin = new JButton();
        buttonLogin.setText("Log In");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButton.add(buttonLogin, gbc);
        buttonRegister = new JButton();
        buttonRegister.setText("Register");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButton.add(buttonRegister, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
