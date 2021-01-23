package model;

import miscellaneous.ColorAssigner;

import app.App;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Pesable;
import model.Tree;

/**
 *
 * @author Mario Chalén
 */
public class TreeMap {

    public static AnchorPane ap;
    public static HBox base;
    public Tree<Pesable> tree;
    private LinkedList<Tree<Pesable>> primeros;

    public TreeMap(File root) {
        tree = createDirectoryTree(root);
        primeros = tree.getChildren();
    }

    private Tree<Pesable> createDirectoryTree(File f) {
        Tree<Pesable> t = new Tree<>();
        t.setRoot(new Pesable(f.getName(), computeSize(f)));
        if (f.list() != null) {
            for (File newFile : f.listFiles()) {
                Tree<Pesable> newTree;
                if (newFile.isDirectory()) {
                    newTree = createDirectoryTree(newFile);
                } else {
                    newTree = new Tree<>(new Pesable(newFile.getName(),
                            newFile.length()));
                }
                t.getChildren().add(newTree);
                t.getChildren().sort((Tree<Pesable> t1, Tree<Pesable> t2) -> {
                    double d = (double) t2.getRoot().getWeight() - t1.getRoot().getWeight();
                    return (int) d;
                });
            }
        }
        return t;
    }

    private long computeSize(File f) {
        if (f.isDirectory()) {
            long totalSize = 0;
            File[] childrens = f.listFiles();
            if (childrens != null) {
                for (File children : childrens) {
                    totalSize += computeSize(children);
                }
            }
            return totalSize;
        } else {
            return f.length();
        }
    }

    public void draw() {
        this.tree.imprimir();
        try {
            App.setRoot("treemap");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        fillPane(tree, TreeMap.base);
    }

    public Background getBackgroundFromColor(Color color) {
        BackgroundFill bgFill = new BackgroundFill(
                color,
                CornerRadii.EMPTY,
                Insets.EMPTY);
        Background bg = new Background(bgFill);
        return bg;
    }

    public void setPaneColor(Pane pane, Pesable p) {
        Background bg = getBackgroundFromColor(ColorAssigner.getColor(p.getName()));
        pane.setBackground(bg);
    }

    public void setPaneTitle(Pane pane, Pesable p) {
        Label lb = new Label(p.toString());
        lb.setTextFill(Color.WHITE);
        VBox vb = new VBox(lb);
        Background bg = getBackgroundFromColor(Color.BLACK);
        lb.setBackground(bg);
        pane.getChildren().add(vb);
    }

    private void fillPane(Tree<Pesable> pesable, Pane base) {
        for (int i = 0; i < pesable.getChildren().size(); i++) {
            Tree<Pesable> p = pesable.getChildren().get(i);

            if (base instanceof VBox) {
                HBox hb = new HBox();

                
                hb.setSpacing(2);
                hb.setPrefHeight(
                        base.getPrefHeight()
                        * ((double) p.getRoot().getWeight()
                        / pesable.getRoot().getWeight()));
                hb.setPrefWidth(base.getPrefWidth());

                setPaneColor(hb, p.getRoot());

                base.getChildren().add(hb);
////                if (this.primeros.contains(p)) {
////                    Label lbl = new Label(p.getRoot().toString());
////                    long total = 0;
////                    for(int j = primeros.indexOf(p); j > 0;j--){
////                        total += primeros.get(j).getRoot().getWeight();
////                    }
////                    lbl.setLayoutX(base.getPrefHeight()
////                        * ((double) total
////                        / pesable.getRoot().getWeight()));
//////                    lbl.setLayoutY(hb.getLayoutY());
////                    ap.getChildren().add(lbl);
////                }
                if (!p.isLeaf()) {
                    fillPane(p, hb);
                }

            } else if (base instanceof HBox) {
                VBox vb = new VBox();

                
                vb.setSpacing(2);
                vb.setPrefWidth(
                        base.getPrefWidth()
                        * ((double) p.getRoot().getWeight()
                        / pesable.getRoot().getWeight()));
                vb.setPrefHeight(base.getPrefHeight());

                setPaneColor(vb, p.getRoot());

                base.getChildren().add(vb);
                if (this.primeros.contains(p)) {
                    Label lbl = new Label(p.getRoot().toString());
                            lbl.setTextFill(Color.WHITE);
        Background bg = getBackgroundFromColor(Color.BLACK);
        lbl.setBackground(bg);
                    long total = 0;
                    for(int j = primeros.indexOf(p)-1; j >= 0;j--){
                        total +=  (1280
                        * ((double) primeros.get(j).getRoot().getWeight()
                        / tree.getRoot().getWeight()));
//                        total += primeros.get(j).getRoot().getWeight();
                    }
                    lbl.setLayoutX((double) total);
//                    lbl.setLayoutY(hb.getLayoutY());
                    ap.getChildren().add(lbl);
                }
                if (!p.isLeaf()) {
                    fillPane(p, vb);
                }
            }
        }
    }
}
