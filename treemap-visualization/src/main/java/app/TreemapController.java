package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
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
        TreeMap.base = base;
    }
    @FXML
    public void regresar(Event e){
        
    }
}
