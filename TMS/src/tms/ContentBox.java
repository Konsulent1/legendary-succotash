package tms;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class ContentBox extends Pane {

    private VBox box;
    Rectangle bg;

    public ContentBox(int width, int height) {

        bg = new Rectangle(width, height);
        bg.setFill(Color.LIGHTGRAY);
        getChildren().addAll(bg);
    }
    
    public void setFill(Paint value){
    	bg.setFill(value);
    }
    
    public void addItems(MenuItem... items) {
        for (MenuItem item : items)
            addItem(item);
    }

    public void addItem(MenuItem item) {
        box.getChildren().addAll(item);
    }
    
}