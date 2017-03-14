/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driverapp;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author oebar
 */
public class DriverApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        StackPane root = new StackPane();
        
        
        Button btnRoute = new Button();
        Button btnReport = new Button();
        Button btnSign = new Button();
        Button btnExportDocument = new Button();
        Button btnLogout = new Button();
        
        root.getChildren().addAll(btnRoute, btnReport, btnSign, btnExportDocument, btnLogout);
        
        btnRoute.setTranslateY(-200);
        btnReport.setTranslateY(-150);
        btnSign.setTranslateY(-100);
        btnExportDocument.setTranslateY(-50);
        btnLogout.setTranslateY(0);
        
        btnRoute.setText("Route");
        btnReport.setText("Report Delay");
        btnSign.setText("Sign");
        btnExportDocument.setText("Export Document");
        btnLogout.setText("Logout");
        
        
        
        
        btnRoute.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                //Logikk her
                
            }
        });
        
        btnReport.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                //Logikk her
                
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
        
        btnLogout.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
              
                //logikk her
                
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
    public static void main(String[] args) {
        launch(args);
    }
    
    public void printList() {
        Route route = new Route();
        ArrayList list = new ArrayList<String>(); 
        list = route.getRoutes();
        
        for(int i = 0; i < list.size(); i++ ){
            System.out.println(list.get(i));
        }
        
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
