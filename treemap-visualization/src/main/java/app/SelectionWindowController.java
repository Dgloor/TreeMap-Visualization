package app;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
import model.TreeMap;

/**
 * FXML Controller class
 *
 * @author Ac 001
 * @author Danny Loor
 */
public class SelectionWindowController implements Initializable {

    Ventana ventana;

    @FXML
    Button botonArchivo;
    @FXML
    Circle btnClose, btnMinimize;
    @FXML
    BorderPane baseScreen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ventana = new Ventana();
    }

    @FXML
    public void seleccionarArchivo(MouseEvent e) {
        DirectoryChooser buscarDirectorio = new DirectoryChooser();
        buscarDirectorio.setTitle("Elegir directorio");
        File selectedDirectory = buscarDirectorio.showDialog(null);
        if (selectedDirectory != null) {
            TreeMap tm = new TreeMap(selectedDirectory);
            tm.draw();
        }
    }

    @FXML
    public void handlePressed(MouseEvent e) {
        ventana.setPosicion(e);
    }

    @FXML
    public void handleDragged(MouseEvent e) {
        ventana.move(e);
    }

    @FXML
    public void handleMin(MouseEvent e) {
        ventana.minimize();
    }

    @FXML
    public void handleClose(MouseEvent e) {
        ventana.close();
    }
}
