/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driverapp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import java.sql.DriverManager;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TablePosition;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 * @author oebar
 */
public class DriverApp extends Application
{

    private Login loginObj;
    private Route route;

    @Override
    public void start(Stage primaryStage)
    {

        route = new Route();

        StackPane root = new StackPane();
        StackPane login = new StackPane();

        Button btnLogin = new Button();
        Button btnRoute = new Button();
        Button btnReport = new Button();
        Button btnSign = new Button();
        Button btnExportDocument = new Button();
        Button btnExit = new Button();

        this.loginObj = new Login();
        int loginValue = 2;
        while (loginValue == 2)
        {
            loginValue = loginObj.loginDialog();
            if (loginValue == 3)
            {
                Platform.exit();
            }
        }

        root.getChildren().addAll(btnRoute, btnReport, btnSign, btnExportDocument, btnExit);
        login.getChildren().addAll(btnLogin);
        btnRoute.setTranslateY(-200);
        btnReport.setTranslateY(-150);
        btnSign.setTranslateY(-100);
        btnExportDocument.setTranslateY(-50);
        btnExit.setTranslateY(0);

        btnRoute.setText("Route");
        btnReport.setText("Report Delay");
        btnSign.setText("Sign");
        btnExportDocument.setText("Export Document");
        btnExit.setText("Exit");

        btnRoute.setOnAction(new EventHandler<ActionEvent>()
        {

            HashMap routeList = route.getRoutes();
            ArrayList listValues = new ArrayList<String>(routeList.values());
            ArrayList listKeys = new ArrayList<String>(routeList.keySet());

            @Override
            public void handle(ActionEvent event)
            {

                for (int i = 0; i < listValues.size(); i++)
                {
                    System.out.println(listValues.get(i) + " " + listKeys.get(i).toString() + "km");
                }
            }
        });

        btnReport.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {

                //Logikk her
                Alert alert;
                AddDelayDialog dDialog = new AddDelayDialog();
                Optional<Rapport> result = dDialog.showAndWait();
                if (dDialog.isButtonOK())
                {
                    try
                    {
                        String delayReason = "" + dDialog.getDelayReason();
                        String delayTime = "" + dDialog.getDelayInMin();
                        /**
                         * INSERT INTO [dbo].[PortOfOrigin] ([OriginID] ,
                         * [Port]) VALUES	(1,	'PortOfAdasalesund'); *
                         */

                        try
                        {
                            Statement stmt = null;
                            Connection connection = getConnection();
                            //  PreparedStatement pst = connection.prepareStatement("INSERT INTO Delay (Reason, DelayTime) VALUES(" + delayReason + ", " + delayTime + ");");
                            //  pst.executeQuery();

                            stmt = connection.createStatement();

                            String sql = "INSERT INTO Delay "
                                    + "VALUES (" + delayReason + ", " + delayTime + ")";
                            stmt.executeUpdate(sql);

                            connection.close();

                        } catch (Exception e)
                        {

                        }

                    } catch (InputMismatchException e)
                    {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("ERROR");
                        alert.setContentText("Invalid entry");
                    } catch (IllegalArgumentException e)
                    {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("ERROR");
                        alert.setContentText("The report is already in the list of reports");
                    }
                } else
                {
                    System.out.println("Error");
                }

            }
        });

        btnSign.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                generateReport();
            }
        });

        btnExportDocument.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    Connection connection = getConnection();
                    PreparedStatement pst = connection.prepareStatement("SELECT * FROM Delay");
                    ResultSet rs = pst.executeQuery();

                    while (rs.next())
                    {
                        System.out.print(rs.getString("Reason") + "  ");
                        System.out.println(rs.getString("DelayTime"));
                        //String test = null;
                        //test = rs.getString(1);
                    }
                    connection.close();

                } catch (Exception e)
                {

                }
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Exit application?");
                alert.setContentText("Are you sure you want to exit?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                {
                    Platform.exit();
                }
            }
        });

        Scene scene = new Scene(root, 300, 600);

        primaryStage.setTitle("DriverGUI");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    public void generateReport()
    {
        System.out.println("All goods delivered");
    }

    public void sign()
    {

    }

    public void exportDocument()
    {

    }

    public void logout()
    {

    }

    public Connection getConnection()
    {
        Connection connection = null;
        try
        {
            String connectionURL = "jdbc:sqlserver://158.38.101.103;"
                    + "databaseName=Konsulent1;user=admin123;password=admin123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionURL);
            if (connection.isValid(0))
            {
                System.out.println("Connection estabished");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return connection;
    }

}
