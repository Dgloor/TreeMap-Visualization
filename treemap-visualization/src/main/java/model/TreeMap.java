package model;

import app.App;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import tda.Tree;

/**
 *
 * @author Mario Chalén
 */
public class TreeMap {
    
    public static HBox base;
    public Tree<Pesable> tree;
    static Random r;

    public TreeMap(File root) {
        tree = createDirectoryTree(root);
        r = new Random();
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
        try {
            App.setRoot("treemap");
        } catch (IOException ex) {
        }
        rellenar(tree, TreeMap.base);
    }
    
    private void rellenar(Tree<Pesable> a, Pane base){
        if (base instanceof VBox){
            for(Tree<Pesable> p : a.getChildren()){
                HBox hb = new HBox();
                hb.setPrefHeight(base.getPrefHeight()*((double)p.getRoot().getWeight()/a.getRoot().getWeight()));
                hb.setPrefWidth(base.getPrefWidth());
                //La siguiente línea fija un color aleatorio para el rectángulo
                hb.setBackground(new Background(new BackgroundFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)),
    CornerRadii.EMPTY,
    Insets.EMPTY)));
                base.getChildren().add(hb);
                if (!p.isLeaf()){
                    rellenar(p, hb);
                }
            }
        }
        else if (base instanceof HBox){
            for(Tree<Pesable> p : a.getChildren()){
                VBox vb = new VBox();
                vb.setPrefWidth(base.getPrefWidth()*((double)p.getRoot().getWeight()/a.getRoot().getWeight()));
                vb.setPrefHeight(base.getPrefHeight());
                //La siguiente línea fija un color aleatorio para el rectángulo
                vb.setBackground(new Background(new BackgroundFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)),
    CornerRadii.EMPTY,
    Insets.EMPTY)));
                base.getChildren().add(vb);
                if (!p.isLeaf()){
                    rellenar(p, vb);
                }
            }
        }
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
