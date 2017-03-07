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
        Button btnRoute = new Button();
        Button btnReport = new Button();
        btnRoute.setText("Route");
        btnReport.setText("Report Delay");
        
        btnRoute.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                printList();
            }
        });
        
        btnReport.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                generateReport();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btnRoute);
        root.getChildren().add(btnReport);
        
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
    
    public void printList(){
        Route route = new Route();
        ArrayList list = new ArrayList<String>();  
        list = route.getRoutes();
        
        for(int i = 0; i < list.size(); i++ ){
            System.out.println(list.get(i));
        }
        
    }
    
    public void generateReport(){
        
    }
    
    
}
