package deque;

public class LinkedListDeque<T> {

    public class IntNode{
        public T item;
        public IntNode pre;
        public IntNode next;

        public IntNode(T f,IntNode p,IntNode n){
            item=f;
            pre=p;
            next=n;
        }
    }

    private IntNode sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel=new IntNode(null,null,null);
        sentinel.pre=sentinel;
        sentinel.next=sentinel;
        size=0;
    }

    public void addFirst(T x){
        IntNode now=new IntNode(x,sentinel,sentinel.next);
        sentinel.next.pre=now;
        sentinel.next=now;
        size+=1;
    }

    public void addLast(T x){
        IntNode now=new IntNode(x,sentinel.pre,sentinel);
        sentinel.pre.next=now;
        sentinel.pre=now;
        size+=1;
    }

    public boolean isEmpty(){
        if(size==0)
            return true;
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        IntNode now =sentinel;
        while(now.next!=sentinel){
            now=now.next;
            System.out.print(now.item+" ");
        }
        System.out.println();
    }

    public T removeFirst(){
        IntNode now=sentinel.next;
        sentinel.next.next.pre=sentinel;
        sentinel.next=sentinel.next.next;
        if(now==sentinel)
            return null;
        size-=1;
        return now.item;
    }

    public T removeLast(){
        IntNode now=sentinel.pre;
        sentinel.pre.pre.next=sentinel;
        sentinel.pre=sentinel.pre.pre;
        if(now==sentinel)
            return null;
        size-=1;
        return now.item;
    }

    public T get(int index){
        IntNode now=sentinel;
        for(int i=0;i<=index;i+=1){
            now=now.next;
            if(now==sentinel)
                return null;
        }
        return now.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(IntNode current, int index) {
        // 基准情况：到达目标索引
        if (index == 0) {
            return current.item;
        }
        // 递归情况：移动到下一个节点，索引减1
        return getRecursiveHelper(current.next, index - 1);
    }
}
