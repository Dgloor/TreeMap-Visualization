package tda;

import java.util.LinkedList;

/**
 *
 * @author Mario Chalén
 */
class TreeNode<T> {
    private T content;
    LinkedList<Tree<T>> children;

    
    public TreeNode(T content){
        this.content = content;
        children = new LinkedList<>();
    }

    public T getContent() {
        return content;
    }

    public void addChild(T content){
        this.children.add(new Tree(content));
    }
    
    public void addChild(Tree newTree){
        this.children.add(newTree);
    }
    
    @Override
    public String toString(){
        return content.toString();
    }
}
