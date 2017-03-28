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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DriverApp extends Application {
    
    
    private Login loginObj;
    private Route route;
    
            
    @Override
    public void start(Stage primaryStage) {
        
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
        while(loginObj.getPassword() == null && loginObj.getUsername() == null){
        loginObj.loginDialog();
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
        btnExit.setText("Logout");
        
        
        
        
        btnRoute.setOnAction(new EventHandler<ActionEvent>() {
            
            HashMap routeList = route.getRoutes();
                ArrayList listValues = new ArrayList<String>(routeList.values());
                ArrayList listKeys = new ArrayList<String>(routeList.keySet());
            
            @Override
            public void handle(ActionEvent event) {
                
                
                for(int i = 0; i<listValues.size(); i++){
                    System.out.println(listValues.get(i) + " " + listKeys.get(i).toString() + "km");
                }
                
                
                
                
            }
        });
        
        btnReport.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {

                
                //Logikk her
      

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                /**Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
>>>>>>> f311f63ca3bc966fff1be1a185cd27d696fc03a4
                alert.setHeaderText("Are you going to be late??");
                alert.setContentText("Are you sure you want to be late?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                {
                    System.out.println("You are going to be late");
                } else
                {
                }**/
                AddDelayDialog dDialog = new AddDelayDialog();
                Optional<Rapport> result = dDialog.showAndWait();
                if (dDialog.isButtonOK())
                {
                    try
                    {
                        String delayReason = "" + dDialog.getDelayReason();
                        String delayTime = "" + dDialog.getDelayInMin();
     /**                   
INSERT INTO [dbo].[PortOfOrigin] ([OriginID] , [Port])
VALUES	(1,	'PortOfAdasalesund'); **/

                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver:hallvbjo-Konsulent1.uials.no;user=hallvbjo;password=hallvbjo;database=Konsulent1");
                        System.out.println("test");
                        Statement sta = conn.createStatement();
                        String Sql = "Insert into Delay" + "VALUES (" + delayReason + ", " + delayTime + ")";
                        ResultSet rs = sta.executeQuery(Sql);
                        conn.close();
                
                    } catch (InputMismatchException e)
                    {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("ERROR");
                        alert.setContentText("Invalid entry");
                    }catch (IllegalArgumentException e)
                    {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("ERROR");
                        alert.setContentText("The report is already in the list of reports");
                    } catch (ClassNotFoundException ex)
                    {
                        Logger.getLogger(DriverApp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex)
                    {
                        Logger.getLogger(DriverApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else
                {
                    System.out.println("Error");
                }

            }
        });
        
        btnSign.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               
                //Logikk her
                
            }
        });
        
        btnExportDocument.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                //Logikk her
                
            }
        });
        
        /*btnExit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
              
                //logikk her
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Exit application?");
                alert.setContentText("Are you sure you want to exit?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                {
                    Platform.exit();
                }
            }
        });*/
        
        
        
        
        
        Scene scene = new Scene(root, 300, 600);
        
        primaryStage.setTitle("DriverGUI");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void generateReport()    {
        System.out.println("All goods delivered");
    }
    
    public void sign()  {
        
    }
    
    public void exportDocument()    {
        
    }
    
    public void logout()    {
        
    }
    
    
    
    
    
}
