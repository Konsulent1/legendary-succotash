package driverapp;


import driverapp.Rapport;
import java.util.InputMismatchException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Creates a pop-up window where the user can add the necessary information for
 * a book. This information includes; The book title, the books delayTime, the
 books author, and the edition of the book.
 *
 * @author Alexander Eilert Berg
 * @version 0.3
 */
public class AddDelayDialog extends Dialog<Rapport>
{

    private String delayReason;
    private String delayInMin;

    private boolean buttonOK = false;

    public AddDelayDialog()
    {
        super();
        setTitle("Delay Details");

        //Add buttons
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField reason = new TextField();
        reason.setPromptText("Delay reason");

        TextField delayTime = new TextField();
        delayTime.setPromptText("Delay time");
        


        reason.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue)
            {
                if (newValue.length() != oldValue.length())
                {
                    delayReason = reason.getText();
                }

            }
        });
        delayTime.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue)
            {

                if (newValue.length() != oldValue.length())
                {
                    delayInMin = delayTime.getText();
                }

            }
        });

        grid.add(new Label("Delay reason:"), 0, 0);
        grid.add(reason, 1, 0);
        grid.add(new Label("Delay time:"), 0, 1);
        grid.add(delayTime, 1, 1);

        getDialogPane().setContent(grid);

        setResultConverter(new Callback<ButtonType, Rapport>()
        {
            @Override
            public Rapport call(ButtonType button)
            {
                if (button == ButtonType.OK)
                {

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

    /**
     * Returns title of the book.
     *
     * @return Returns title of the book.
     */
    public String getDelayReason()
    {
        return delayReason;
    }

    /**
     * Returns the books delayTime.
     *
     * @return Returns the books delayTime.
     */
    public String getDelayInMin()
    {
        return delayInMin;
    }

}
