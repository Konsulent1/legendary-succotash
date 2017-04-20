/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driverapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 *
 * @author oebar
 */
public class AddSignature extends Dialog<Rapport> {
    
    
    private String signatureString;

    private boolean buttonOK = false;

    public AddSignature()
    {
        super();
        setTitle("Delay Details");

        //Add buttons
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField signature = new TextField();
        signature.setPromptText("Signature here");
        
        


        signature.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue)
            {
                if (newValue.length() != oldValue.length())
                {
                    signatureString = signature.getText();
                    
                }
                
            }
        });
        

        grid.add(new Label("Signature:"), 0, 0);
        grid.add(signature, 1, 0);
       
       
        
        getDialogPane().setContent(grid);

        setResultConverter(new Callback<ButtonType, Rapport>()
        {
            @Override
            public Rapport call(ButtonType button)
            {
                if (button == ButtonType.OK)
                {
                    
               System.out.println(signatureString);
                
                    buttonOK = true;
                }
                return null;
            }
        });

    }

    /**
     * Returns the status of the OK button.
     *
     * @return Returns the status of the OK button.
     */
    public boolean isButtonOK()
    {
        return buttonOK;
    }

}

    
    

