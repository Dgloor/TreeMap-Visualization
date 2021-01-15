/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import tda.Tree;

/**
 * FXML Controller class
 *
 * @author Ac 001
 */
public class SelectionWindowController implements Initializable {

    public static Tree<String> directoryTree=new Tree<>();
    @FXML
    Button botonArchivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void seleccionarArchivo() {
        DirectoryChooser buscarDirectorio = new DirectoryChooser();
        buscarDirectorio.setTitle("Elegir directorio");
        File selectedDirectory = buscarDirectorio.showDialog(null);
        directoryTree = directoryTree.createDirectoryTree(selectedDirectory);

    }
}
