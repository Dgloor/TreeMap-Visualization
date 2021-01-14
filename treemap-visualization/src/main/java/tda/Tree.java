package tda;

import java.util.LinkedList;

/**
 *
 * @author Mario Chalén
 * @param <T>
 */
public class Tree<T> {
    private TreeNode<T> root;
    
    public Tree(T content){
        this.root = new TreeNode(content);
    }
    
    public Tree(){
        this.root = null;
    }
    
    public void setRoot(T content){
        this.root = new TreeNode(content);
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public void clear(){
        root = null;
    }

    public LinkedList<T> preOrder(){
        LinkedList<T> elementos = new LinkedList<>();
        return elementos;
    }
    
    public LinkedList<T> inOrder(){
        LinkedList<T> elementos = new LinkedList<>();
        return elementos;
    }
    
    public LinkedList<T> postOrder(){
        LinkedList<T> elementos = new LinkedList<>();
        return elementos;
    }
        
    @Override
    public String toString() {
        return "";
    }    
}
