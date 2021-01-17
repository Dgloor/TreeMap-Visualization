package tda;

import java.util.LinkedList;

/**
 *
 * @author Mario Chalén
 */
class TreeNode<T> {
    private T content;
    LinkedList<Tree<T>> children;
    
    private boolean isFile;
    private long size;
    
    public TreeNode(T content){
        this.content = content;
        children = new LinkedList<>();
        isFile = true;
    }

    public T getContent() {
        return content;
    }

    public boolean isFile(){
        return isFile;
    }
    
    public void setIsFile(boolean b){
        isFile = b;
    }
    
    public void addChild(T content){
        this.children.add(new Tree(content));
    }
    
    public void addChild(Tree newTree){
        this.children.add(newTree);
    }
    
    public long size(){
        if (isFile) {
            return size;
        }
        else {
            long totalSize = 0;
            for (Tree<T> e : children) {
                totalSize += e.root.size();
            }
            return totalSize;
        }
    }
    
    public void setSize(long size){
        this.size = size;
    }
    
    @Override
    public String toString(){
        return content.toString();
    }
}
