package datastructure.queue;

import datastructure.stack.Node;

/**
 * Created by Sang on 10/16/17.
 */
public class LLQueue<T> implements MyQueue<T>{

    private Node<T> head;
    private Node<T> tail;
    private int count;

    @Override
    public boolean insert(T item) {
        Node node = new Node<T>(item);
        if(isEmpty()){
            tail = head = node ;
        }else{
            tail.setNext(node);
            tail = node;
        }
        count++;
        return true;
    }

    @Override
    public boolean ifFull() {
        return false;
    }

    @Override
    public T remove() {
        if(isEmpty()){
            return null;
        }
        T returnee = head.getVal();
        head = head.getNext();
        count--;
        return returnee;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

}
