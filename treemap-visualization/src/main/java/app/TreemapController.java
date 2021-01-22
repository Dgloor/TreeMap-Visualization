package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import model.TreeMap;

/**
 * FXML Controller class
 *
 * @author Mario Chalén
 */
public class TreemapController implements Initializable {

    @FXML HBox base;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base.setSpacing(2);
        TreeMap.base = base; 
        setExitEvent();
    }
    
    public void setExitEvent() {
        App.scene.setOnKeyPressed(event -> {
            if ("ESCAPE".equals(event.getCode().toString())) {
                try {
                    App.setRoot("selectionWindow");
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });
    }
}
