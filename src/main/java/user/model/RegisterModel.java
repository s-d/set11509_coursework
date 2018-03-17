package user.model;

import user.controller.LoginController;
import data.DatabaseConnection;
import data.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class RegisterModel extends Observable {
    private final int REGISTER = 0, INVALID = -1, EXIST = -2;
    private DatabaseConnector databaseConnector;

    public RegisterModel() {
        databaseConnector = new DatabaseConnection();
    }

    public void attemptRegisterUser(String userName, String userPassword) {
        LoginController.RESULT result = LoginController.RESULT.FAILED;

        if (checkInvalidCredential(userName) || checkInvalidCredential(userPassword)) {
            System.out.println("RegisterModel: user credential invalid");
            result = LoginController.RESULT.INVALID_CREDENTIAL;
        } else if (checkUserExists(userName)) {
            System.out.println("RegisterModel: user already exists");
            result = LoginController.RESULT.USER_EXISTS;
        } else {
            registerUser(userName, userPassword);
            System.out.println("RegisterModel: user added to database");
            result = LoginController.RESULT.REGISTERED;
        }
        setChanged();
        notifyObservers(result);
    }

    private boolean checkUserExists(String userName) {
        boolean exists = true;
        databaseConnector.connect();
        try {
            ResultSet result = databaseConnector.query(generateUserExistsQuery(userName));
            exists = result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.closeConnection();
        return exists;
    }

    private boolean registerUser(String userName, String userPassword) {
        boolean registered;
        databaseConnector.connect();
        try {
            databaseConnector.query(generateRegisterUserQuery(userName, userPassword));
            registered = true;
        } catch (SQLException e) {
            e.printStackTrace();
            registered = false;
        }

        databaseConnector.closeConnection();
        return registered;
    }

    private boolean checkInvalidCredential(String credential) {
        return (credential == null || credential.isEmpty());
    }

    private String generateUserExistsQuery(String userName) {
        return String.format("SELECT * " +
                        "FROM user " +
                        "WHERE user_name = '%1$s';"
                , userName);
    }

    private String generateRegisterUserQuery(String userName, String userPassword) {
        return String.format("INSERT " +
                        "INTO user (user_name, user_password, user_admin) " +
                        "VALUES ('%1$s', '%2$s', 0);"
                , userName, userPassword);
    }

}
