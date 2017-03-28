/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driverapp;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 * @author oebar
 */
public class Login
{

    private String password = null;
    private String username = null;

    public String getPassword()
    {
        return this.password;
    }

    public String getUsername()
    {
        return this.password;
    }

    public void loginDialog()
    {

        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("Enter username and password");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue)
                -> 
                {
                    loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton
                -> 
                {
                    if (dialogButton == loginButtonType)
                    {
                        return new Pair<>(username.getText(), password.getText());
                    }
                    return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword
                -> 
                {
                    this.username = usernamePassword.getKey();
                    this.password = usernamePassword.getValue();
                    checkPasswordAndUsername();
        });

    }

    /*
    Used in function logindialog, checks if user exists and if password is correct
    If username or password does not exists, reset username to null
     */
    private void checkPasswordAndUsername()
    {

        try
        {
            Connection connection = getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM UserLogin");
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while(i <= rs.getFetchSize())
            {
                rs.next();
                if (this.password.equals(rs.getString("UserPassword")) && this.username.equals(rs.getString("Username")))
                {
                    System.out.println("ACCESS GRANTED");
                    return;
                } else
                {
                    System.out.println("ACCESS DENIED");
                    
                }
                i++;
            }

            connection.close();
        } catch (Exception e)
        {

        }
    }

    public Connection getConnection()
    {
        Connection connection = null;
        try
        {
            String connectionURL = "jdbc:sqlserver://158.38.101.103;"
                    + "databaseName=Konsulent1;user=hallvbjo;password=hallvbjo;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionURL);

        } catch (Exception e)
        {
        }

        return connection;
    }

}
