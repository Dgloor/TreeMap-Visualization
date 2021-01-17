package app;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import model.TreeMap;

/**
 * FXML Controller class
 *
 * @author Ac 001
 */
public class SelectionWindowController implements Initializable {

    @FXML
    Button botonArchivo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botonArchivo.setOnMouseClicked((e) -> {
            seleccionarArchivo();
        });
    }

    private void seleccionarArchivo() {
        DirectoryChooser buscarDirectorio = new DirectoryChooser();
        buscarDirectorio.setTitle("Elegir directorio");
        File selectedDirectory = buscarDirectorio.showDialog(null);
        TreeMap tm = new TreeMap(selectedDirectory);
        tm.draw();
        //directoryTree = directoryTree.createDirectoryTree(selectedDirectory);;
    }
}
