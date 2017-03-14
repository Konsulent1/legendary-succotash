package tms;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class LoginBox extends Pane {

    Rectangle bg;

    public LoginBox(int width, int height) {
        bg = new Rectangle(width, height);
        bg.setFill(Colors.MENU_BG);
        getChildren().addAll(bg);
    }
    
    public void setFill(Paint value){
    	bg.setFill(value);
    }
}
