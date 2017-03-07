/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tms;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Kristian
 */
public class TMS extends Application {
    
    Screen screen;
    
    @Override
    public void start(Stage primaryStage) {
        
        Scene scene = new Scene(createContent(), 1350, 850);       
        primaryStage.setTitle("Transport Management System");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public Parent createContent(){
        Pane root = new Pane();
        
        int height = 850;
        int width = 1350;
        
        int menuHeight = height;
        int menuWidth =  (int) (width * 0.30);
        
        int contentHeight = height;
        int contentWidth =  width - menuWidth;
        
        //System.out.println(menuHeight + " , " + menuWidth);
        //System.out.println(contentHeight + " , " + contentWidth);
        
        customPane menuPane = new customPane(menuWidth, menuHeight, "right");
        customPane contentPane = new customPane(contentWidth, contentHeight, "noLine");
        contentPane.setTranslateX(menuWidth);
        
        root.getChildren().addAll(menuPane, contentPane);
        
        
        return root;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
