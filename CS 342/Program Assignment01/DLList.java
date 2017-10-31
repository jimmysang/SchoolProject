package datastructure.arrayandlist.homework;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Sang on 2017/9/25.
 */
public class DLList implements Serializable{
    private Node head;
    private Node cursor;


    public DLList(){

    }

    //add a node into right position in the list
    public void add(Node node){
        //if the list is empty, just set this node to head
        if(head == null){
            head = node;
        }else{

            //if node should be in front of head
            if(isAfter(head, node)){
                head.setPrevious(node);
                node.setNext(head);
                head = node;
                return; &
            }
            ListIterator i = this.iterator();
            while(i.hasNext()){
                Node current = i.next();
                if(isAfter(current,node)){
                    addAfter(node,current.getPrevious());
                    return;
                }
            }
            //if the node is the last node
            addAfter(node,i.getCurrent());
        }

    }
    //to see if node1 should be sorted after node2 or not
    public boolean isAfter(Node node1, Node node2){
        return node1.getLastName().compareToIgnoreCase(node2.getLastName()) > 0;
    }

    //add a node after pos
    public void addAfter(Node node, Node pos){
        if(pos.getNext()!=null)pos.getNext().setPrevious(node);
        node.setNext(pos.getNext());
        node.setPrevious(pos);
        pos.setNext(node);
    }



    //delete a node at specific index
    public void delete(int index){
        if(head == null){
            //if the list is empty, return
            return;
        }
        if(index == 0){
            //delete head
            head = head.getNext();
            if(head!=null)head.setPrevious(null);
            return;
        }
        ListIterator<Node> iterator = this.iterator();
        while(iterator.hasNext()){
            Node node = iterator.next();
            if(index == iterator.getIndex()){
                delete(node);
                return;
            }
        }
        System.out.println("No node was deleted.");
    }

    //delete the node given its reference
    public void delete(Node node){
        if(node.getPrevious()!=null)node.getPrevious().setNext(node.getNext());
        if(node.getNext()!=null)node.getNext().setPrevious(node.getPrevious());
    }

    public String toString(){
        //implement print method in list
        StringBuffer rtn =  new StringBuffer("");
        if(head == null){
            rtn.append("Your telephone list is empty now, try to add some contacts.");
        }else{
            ListIterator i = this.iterator();
            while(i.hasNext()){
                Node node = i.next();
                rtn.append(String.format("Index: [%s]\n",i.getIndex()));
                rtn.append(node.toString());
                rtn.append("\n");
            }
        }
        return rtn.toString();
    }
    //traverse the lis to search for name
    public String searchForName(String name){
        ListIterator i = this.iterator();
        while (i.hasNext()){
            Node node = i.next();
            if(node.getName().toLowerCase().contains(name.toLowerCase())){
                return String.format("Index: [%s]\n",i.getIndex()) + node.toString();
            }
        }
        return null;
    }
    //traverse the lis to search for email
    public String searchForEmail(String email){
        ListIterator i = this.iterator();
        while (i.hasNext()){
            Node node = i.next();
            if(node.getEmail().toLowerCase().contains(email.toLowerCase())){
                return String.format("Index: [%s]\n",i.getIndex()) + node.toString();
            }
        }
        return null;
    }
    // implement an iterator in order to make other method easier to implement
    class ListIterator<T> implements Iterator<Node>,Serializable{

        private Node cursor;
        private int index;
        private Node previous;

        public ListIterator() {
            this.cursor = head;
            index = -1;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Node next() {
            previous = cursor;
            cursor = cursor.getNext();
            index++;
            return previous;
        }
        //return current node's index
        public int getIndex() {
            return index;
        }

        public Node getCurrent(){
            return previous;
        }
    }

    //iterator constructor
    public ListIterator<Node> iterator() {
        return new ListIterator();
    }
}
