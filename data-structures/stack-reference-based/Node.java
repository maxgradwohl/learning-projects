/* Filename: Node.java
 * Author: Max Gradwohl
 * Date: June 21, 2018
 * Description: Implementation of Node for a linked list stack
 */

public class Node {
    private Object item;
    private Node next;

    public Node(Object newItem, Node newNext) {
        item = newItem;
        next = newNext;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node newNext) {
        next = newNext;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object newItem) {
        item = newItem;
    }
}
