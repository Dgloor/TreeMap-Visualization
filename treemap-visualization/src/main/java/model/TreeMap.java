package model;

import java.io.File;
import model.Directorio;
import model.Pesable;
import tda.Tree;

/**
 *
 * @author Mario Chalén
 */
public class TreeMap {

    public Tree<Pesable> tree;

    public TreeMap(File root) {
        tree = createDirectoryTree(root);
    }

    private Tree<Pesable> createDirectoryTree(File f) {      
        Tree<Pesable> t = new Tree<>();
//        t.setRoot(f);
//        for (String elemento : f.list()) {
//            File newFile = new File(f.getAbsolutePath() + "/" + elemento);
//            Tree<File> newTree = new Tree<>(newFile);
//    //                newTree.setSize(newFile.length());
//            if (newFile.isDirectory()) {
//                newTree = createDirectoryTree(newFile);
//    //                    newTree.setIsFile(false);
//            }
//            t.getChildren().add(newTree);
//        }
        return t;
        
    }

    public void draw() {
        this.tree.imprimir();
    }

}
