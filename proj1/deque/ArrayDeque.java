package deque;

import java.lang.reflect.Array;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items=(T[]) new Object[8];
        size=0;
        nextFirst=0;
        nextLast=1;
    }

    public void addFirst(T x){
        if(size==items.length){
            resize(size*2);
        }
        items[nextFirst]=x;
        nextFirst=(nextFirst-1+items.length)%items.length;
        size+=1;
    }

    public void addLast(T x){
        if(size==items.length){
            resize(size*2);
        }
        items[nextLast]=x;
        nextLast=(nextLast+1)%items.length;
        size+=1;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int i;
        for(i=0;i<size;i+=1){
            int index=(nextFirst+1+i)% items.length;
            System.out.print(items[index]+" ");
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty())
            return null;
        if((size== items.length/4)&&(size>=16)){
            resize(items.length/4);
        }
        int index=(nextFirst+1)% items.length;
        T item=items[index];
        items[index]=null;
        nextFirst=index;
        size-=1;
        return item;
    }

    public T removeLast(){
        if(isEmpty())
            return null;
        if((size== items.length/4)&&(size>=16)){
            resize(items.length/4);
        }
        int index=(nextLast-1+ items.length)% items.length;
        T item=items[index];
        items[index]=null;
        nextLast=index;
        size-=1;
        return item;
    }

    public T get(int index){
        if(size<0||index>=size)
            return null;
        int actualIndex=(nextFirst+1+index)%items.length;
        return items[actualIndex];
    }

    public void resize(int capacity){
        T[] newItems=(T[]) new Object[capacity];
        for(int i=0;i<size;i+=1){
            int index=(nextFirst+1+i)% items.length;
            newItems[i]=items[index];
        }
        items=newItems;
        nextFirst=capacity-1;
        nextLast=size;
    }
}
