package model;

import java.io.File;
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
        t.setRoot(new Directorio(f.getName(), size(f)));
        for (String elemento : f.list()) {
            File newFile = new File(f.getAbsolutePath() + "/" + elemento);
            Tree<Pesable> newTree;
            if (newFile.isDirectory()) {
                newTree = createDirectoryTree(newFile);
            } else {
                newTree = new Tree<>(new Archivo(newFile.getName(), newFile.length()));
            }
            t.getChildren().add(newTree);
        }
        return t;
    }

    public void draw() {
        this.tree.imprimir();
    }

    private long size(File f){
        if (f.isFile()) {
            return f.length();
        }
        else {
            long totalSize = 0;
            for (String elemento : f.list()) {
                File newFile = new File(f.getAbsolutePath() + "/" + elemento);
                totalSize += size(newFile);
            }
            return totalSize;
        }
    }
}
