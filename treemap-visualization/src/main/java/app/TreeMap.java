package app;

import java.io.File;
import tda.Tree;

/**
 *
 * @author Mario Chalén
 */
public class TreeMap {
    public Tree<File> mapeo;
    
    public TreeMap(File root){
        mapeo = createDirectoryTree(root);
    }
    
    private Tree<File> createDirectoryTree(File f){
         if(!f.exists()) {
            return null;
        } else {
            Tree<File> mapa = new Tree<>();
            mapa.setRoot(f);
            for (String elemento : f.list()) {
                File newFile = new File(f.getAbsolutePath()+"/"+elemento);
                Tree<File> newTree = new Tree<>(newFile);
                newTree.setSize(newFile.length());
                if (newFile.isDirectory()){
                    newTree = createDirectoryTree(newFile);
                    newTree.setIsFile(false);
                }
                mapa.getChildren().add(newTree);
            }
            return mapa;
        }
    }
    
}
