/* Filename: StackReference.java
 * Author: Max Gradwohl
 * Date: June 21, 2018
 * Description: Implements a linked list based stack
 */

import java.util.EmptyStackException;

public class StackReference {
    private Node top;

    public StackReference() {
        top = null;
    }

    public Node getTop() {
        return top;
    }
    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
    }

    public void push(Object newItem) {
        top = new Node(newItem, top);
    }

    public Object pop() throws EmptyStackException {
        if (!isEmpty()) {
            Node temp = top;
            top = top.getNext();
            return temp.getItem();
        }
        else {
            throw new EmptyStackException(); 
        }
    }

    public Object peek() throws EmptyStackException {
        if (!isEmpty()) {
            return top.getItem();
        }
        else {
            throw new EmptyStackException();
        }
    }
    
}
