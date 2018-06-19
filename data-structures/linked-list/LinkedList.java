/* Filename: LinkedList.java
 * Author: Max Gradwohl
 * Description: Implements LinkedList ADT using "Nodes"
 * Date: June 18, 2018
 */

public class LinkedList {
	// Instance variables
	private int numItems;
	private Node head;

	// Default constructor creates empty list
	public LinkedList() {
		head = null;
		numItems = 0;
	}
	
	public Node getHead() {
		return head;
	}
	
	// Adds a new Node to list which becomes head Node
	// @param data - the data to be stored in the new Node
	public void add(int data) {
		if (numItems == 0) {
			head = new Node(data, null);
		}
		else {
			temp = new Node(data, head);
			head = temp;
		}
		numItems++;
	}

	// Removes head (first) Node and returns its data
	// If list is empty returns -1
	public int removeHead() {
		if (numItems == 0) {
			return -1;
		}
		else {
			int temp = head.getData();
			head = head.getNext();
			numItems--;
			return temp;
		}
	}

	// Destroys list by setting head to null
	public void clearList() {
		head = null;
	}

	// Returns true if list is empty
	public boolean isEmpty() {
		return numItems == 0;
	}

	public void print() {
		if (isEmpty) {
			System.out.println("The list is empty");
		}
		else {
			Node cur = head;
			while (cur.getNext() != null) {
				System.out.println(cur.getData());
				cur = cur.gextNext();
			}
		}
	}

}
