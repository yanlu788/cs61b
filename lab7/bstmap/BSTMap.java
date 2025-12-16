package bstmap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    int size=0;
    BSTNode root=null;

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<BSTNode> stack = new Stack<>();
        private BSTNode current;

        public BSTIterator() {
            current = root;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        @Override
        public K next() {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            BSTNode node = stack.pop();
            current = node.right;
            return node.key;
        }
    }

    private class BSTNode{
        K key;
        V val;
        BSTNode left;
        BSTNode right;

        BSTNode(K k,V v,BSTNode l,BSTNode r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }

        BSTNode get(K k){
            BSTNode current=this;
            while(current!=null){
                int cmp=k.compareTo(current.key);
                if(cmp==0)
                    return current;
                else if(cmp<0)
                    current=current.left;
                else
                    current=current.right;
            }
            return null;
        }

        BSTNode insert(K k, V v) {
            BSTNode current = this;
            BSTNode parent = null;
            boolean isLeftChild = false;

            // 查找插入位置
            while (current != null) {
                parent = current;
                int cmp = k.compareTo(current.key);
                if (cmp < 0) {
                    current = current.left;
                    isLeftChild = true;
                } else if (cmp > 0) {
                    current = current.right;
                    isLeftChild = false;
                } else {
                    // 键已存在，更新值
                    current.val = v;
                    return this;
                }
            }

            // 创建新节点
            BSTNode newNode = new BSTNode(k, v,null,null);
            // 连接到父节点
            if (isLeftChild) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            return this;
        }
    }

    @Override
    public void clear(){
        size=0;
        root=null;
    }

    @Override
    public boolean containsKey(K key){
        if(root==null)
            return false;
        return root.get(key)!=null;
    }

    @Override
    public V get(K key){
        if(root==null){
            return null;
        }
        BSTNode lookup=root.get(key);
        if(lookup==null)
            return null;
        return lookup.val;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void put(K key,V val){
        if(root!=null){
            root.insert(key,val);
            size+=1;
        }else{
            root=new BSTNode(key,val,null,null);
            size+=1;
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

}
