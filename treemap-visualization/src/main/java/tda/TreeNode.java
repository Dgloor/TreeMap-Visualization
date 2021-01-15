package tda;

import java.util.LinkedList;

/**
 *
 * @author Mario Chalén
 */
class TreeNode<T> {
    private T content;
    private LinkedList<Tree<T>> children;
    
    private boolean isFile;
    private long size;
    
    public TreeNode(T content){
        this.content = content;
        children = new LinkedList<>();
    }
    
    public void addChild(T content){
        this.children.add(new Tree(content));
    }
    
    public void addChild(Tree newTree){
        this.children.add(newTree);
    }
    public void setSize(long size){
        this.size=size;
    }
    
}
