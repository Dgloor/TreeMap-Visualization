package model;

import miscellaneous.ColorAssigner;

import app.App;
import java.io.File;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Mario Chalén
 */
public class TreeMap {

    public static HBox base;
    public Tree<Pesable> tree;

    public TreeMap(File root) {
        tree = createDirectoryTree(root);
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

                if (i == 0) {
                    setPaneTitle(hb, pesable.getRoot());
                }

                hb.setSpacing(2);
                hb.setPrefHeight(
                        base.getPrefHeight()
                        * ((double) p.getRoot().getWeight()
                        / pesable.getRoot().getWeight()));
                hb.setPrefWidth(base.getPrefWidth());

                setPaneColor(hb, p.getRoot());

                base.getChildren().add(hb);
                if (!p.isLeaf()) {
                    fillPane(p, hb);
                }

            } else if (base instanceof HBox) {
                VBox vb = new VBox();

                if (i == 0) {
                    setPaneTitle(vb, pesable.getRoot());
                }

                vb.setSpacing(2);
                vb.setPrefWidth(
                        base.getPrefWidth()
                        * ((double) p.getRoot().getWeight()
                        / pesable.getRoot().getWeight()));
                vb.setPrefHeight(base.getPrefHeight());

                setPaneColor(vb, p.getRoot());

                base.getChildren().add(vb);
                if (!p.isLeaf()) {
                    fillPane(p, vb);
                }
            }
        }
    }
}
