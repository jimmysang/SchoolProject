package datastructure.arrayandlist.homework;
import java.io.Serializable;

/**
 * Created by Sang on 2017/9/25.
 */
public class Node implements Serializable{
    private String name;
    private String email;
    private String phoneNum;

    private Node next;
    private Node previous;

    public Node(String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        next = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    //use this to get last name easily
    public String getLastName(){
        String[] names = this.name.split(" ");
        return names[names.length - 1];
    }

    //convert the node to string
    @Override
    public String toString() {
        StringBuffer rtn = new StringBuffer();
        rtn.append("Name: " + this.getName() + "\n");
        rtn.append("-email: " + this.getEmail() + "\n");
        rtn.append("-Phone number: " + this.getPhoneNum() + "\n");
        return rtn.toString();
    }
}
