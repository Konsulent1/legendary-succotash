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
        
        Scene scene = new Scene(createContent());
        /*
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        */
        

        
        //root.getChildren().add(btn);
        
        
        
        //Scene scene = new Scene(root);
        
        primaryStage.setTitle("Hello World!");
        //primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public Parent createContent(){
        Pane root = new Pane();
        
        int height = 800;
        int width = 1800;
        
        int menuHeight = height;
        int menuWidth =  (int) (width * 0.30);
        
        int contentHeight = height;
        int contentWidth =  width - menuWidth;
        
        //screen = Screen.getPrimary();
        //Rectangle2D bounds = screen.getVisualBounds();
        //int height = (int) bounds.getHeight() - 100;
        //int width = (int) (bounds.getWidth() * 0.30);
        //System.out.println(width + " , " + height);
        
        
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
