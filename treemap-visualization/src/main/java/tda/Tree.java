package tda;

import java.io.File;
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
    
    public Tree<String> createDirectoryTree(File path){
         if(!path.exists()){
            return null;
        }else{
            Tree<String> mapa=new Tree<>();
            mapa.setRoot(path.getAbsolutePath());
            mapa.root.setSize(path.getTotalSpace());
            if(path.isDirectory()){
                  File[] childrens=path.listFiles();
                  if (childrens!=null && childrens.length>0){
                       for(File children: childrens){
                      Tree<String> subTree=createDirectoryTree(children);
                      mapa.root.addChild(subTree);
                  }
                  }
                  return mapa;
            }else{              
                return mapa;
            }
        }
    }
        
    @Override
    public String toString() {
        return "";
    }    
}
