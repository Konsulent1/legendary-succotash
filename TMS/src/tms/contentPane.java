/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tms;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Kristian
 */
public class contentPane extends Pane {
    private VBox box;
    Rectangle lineRight;
    
    public contentPane(int width, int height){
        

        Rectangle bg = new Rectangle(width, height);
        bg.setFill(Color.WHITE);
        bg.setStyle("-fx-padding: 0 0 0 0");
        this.setStyle("-fx-padding: 0 0 0 0");
        
        
        //if(line.equals("right")){
            lineRight = new Rectangle(2, height);
            lineRight.setTranslateX(width - 2);
            lineRight.setFill(Color.BLACK);
            lineRight.setStroke(Color.BLACK);
            //bg.setFill(Color.GRAY);
        //}


        /*
        Rectangle lineTop = new Rectangle(width, 2);
        lineTop.setFill(Color.rgb(25, 250, 25));
        lineTop.setStroke(Color.BLACK);

        Rectangle lineBot = new Rectangle(width, 2);
        lineBot.setTranslateY(height - 2);
        lineBot.setFill(Color.rgb(25, 250, 25));
        lineBot.setStroke(Color.BLACK);

*/
        //box = new VBox(0);    //5
        //box.setTranslateX(0); //25
        //box.setTranslateY(0); //25

        getChildren().addAll(bg);
    } 
    
}
