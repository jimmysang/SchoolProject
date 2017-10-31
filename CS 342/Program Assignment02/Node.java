package datastructure.stack;

/**
 * Created by Sang on 10/13/17.
 */
public class Node<T> {
    T val;
    Node next;
    private final Integer a = 5;

    public Node(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
