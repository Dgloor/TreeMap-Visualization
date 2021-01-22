package model;

import miscellaneous.ColorAssigner;

import app.App;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
        if(f.list() !=null ){
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

    public void setPaneColor(Pane pane, Pesable p) {
        BackgroundFill bgFill = new BackgroundFill(
                ColorAssigner.getColor(p.getName()),
                CornerRadii.EMPTY,
                Insets.EMPTY);
        Background bg = new Background(bgFill);
        pane.setBackground(bg);
    }
    
    public void setPaneTitle(Pane pane, Pesable p) {
        String title = p.toString();
        Label lb = new Label(title);
       
         
        BackgroundFill bgFill = new BackgroundFill(
                ColorAssigner.getColor(p.getName()),
                CornerRadii.EMPTY,
                Insets.EMPTY);
        Background bg = new Background(bgFill);
        
        lb.setBackground(bg);
        
        base.getChildren().add(lb);
    }

    private void rellenar(Tree<Pesable> a, Pane base) {
        setPaneTitle(base, a.getRoot());
        
        if (base instanceof VBox) {
            for (Tree<Pesable> p : a.getChildren()) {
                HBox hb = new HBox();
                hb.setSpacing(2);
                hb.setPrefHeight(base.getPrefHeight() * ((double) p.getRoot().getWeight() / a.getRoot().getWeight()));
                hb.setPrefWidth(base.getPrefWidth());

                setPaneColor(hb, p.getRoot());

                base.getChildren().add(hb);
                if (!p.isLeaf()) {
                    rellenar(p, hb);
                }
            }
        } else if (base instanceof HBox) {
            for (Tree<Pesable> p : a.getChildren()) {
                VBox vb = new VBox();
                vb.setSpacing(2);
                vb.setPrefWidth(base.getPrefWidth() * ((double) p.getRoot().getWeight() / a.getRoot().getWeight()));
                vb.setPrefHeight(base.getPrefHeight());

                setPaneColor(vb, p.getRoot());

                base.getChildren().add(vb);
                if (!p.isLeaf()) {
                    rellenar(p, vb);
                }
            }
        }
    }

    private long size(File f) {
        if(f.isDirectory()){
            long totalSize=0;
            File[] childrens = f.listFiles();
            if(childrens!=null && childrens.length>0){
                 for(File children:childrens){
                totalSize+= size(children);            
            }
            }     
            return totalSize;
        }else{
        return f.length();
        }
    }
}
