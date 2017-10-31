package datastructure.queue;

/**
 * Created by Sang on 10/16/17.
 */
public interface MyQueue<T> {
    boolean insert(T item);

    boolean ifFull();

    T remove();

    boolean isEmpty();

    int size();

    String toString();
}
