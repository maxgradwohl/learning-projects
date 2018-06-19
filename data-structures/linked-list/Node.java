/* Filename: Node.java
 * Author: Max Gradwohl
 * Description: Implements "Node" for use in a linked list
 * Date: June 18, 2018
 */

public class Node {
	// Instance variables
	private int data;
	private Node next;

	// Constructors - one defaults to null values, one for specified values
	public Node() {
		data = null;
		next = null;
	}

	public Node(int newData, Node nextNode) {
		data = newData;
		next = nextNode;
	}

	// Get and set methods for node fields
	public int getData() {
		return data;
	}

	public void setData(int newData) {
		data = newData;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(node newNext) {
		next = newNext;
	}
}
