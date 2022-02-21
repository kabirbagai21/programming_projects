
/**
 * Name: Kabir Bagai
 * Email: kbagai@ucsd.edu
 * Sources used: None
 * 
 * The file contains two classes, MyLinkedList and a nested class, Node, 
 * which is used to intialize and store data/pointer for the nodes in the list. 
 * MyLinkedList implements the methods characteristic of linked lists in Java. 
 */

import java.util.AbstractList;


/** 
 * Contains nested class, Node, and methods that perform 
 * the same operations as linked lists in the Java API. 
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */

	/** 
	* initializes head and tail dummy nodes and sets size of list
	* equal to zero 
	*/
	public MyLinkedList() {
		
		Node headSentinel = new Node(null);
		Node tailSentinel = new Node(null);
		this.head = headSentinel; 
		this.tail = tailSentinel; 
		this.head.next = this.tail; 
		this.tail.prev = this.head; 
		this.size = 0; 
	}

	/** 
	* @return the size of the list
	*/

	@Override
	public int size() {

		return this.size; 
	}

	/** 
	* returns the data of the node at the specified index
	* @param index - index of element to get
	* @return data of node at index
	*/

	@Override
	public E get(int index) {

		//exception if index is greater than size or less than zero
		
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException(); 
		}

		//use getNth to get specified node 

		return this.getNth(index).getElement(); 
		 
		
	}

	/** 
	* adds a node with specified data at the specified index 
	* @param index - position where node should be added
	* @param data - data of node to be added
	*/

	@Override
	public void add(int index, E data) {

		//exception if data in new node is null
		if (data == null){
			throw new NullPointerException(); 
		}

		//exception if index is greater than size or less than zero

		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException(); 
		}

		//intialize new node and nodes to reference next/prev
		Node newNode = new Node(data);
		Node prevNode = new Node(null);
		Node nextNode = new Node(null); 

		//adding to empty list
		if(this.size == 0){
			newNode.next = this.tail; 
			newNode.prev = this.head; 
			this.head.next = newNode;
			this.tail.prev = newNode; 
		
		}

		else{
			//if adding to beginning of list
			if(index == 0){
				prevNode = this.head; 
				nextNode = this.getNth(index); 
			}

			//if adding to end of list

			else if(index == this.size){

				prevNode = this.getNth(index - 1); 
				nextNode = this.tail; 
			}

			//if adding to middle of list
			
			else if(index != 0 || index != this.size){
				prevNode = this.getNth(index - 1);
				nextNode = this.getNth(index);
			}
			
			newNode.next = nextNode;
			newNode.prev = prevNode; 
			nextNode.prev = newNode;
			prevNode.next = newNode; 
		}
	
		size++; 

	}

	/** 
	* adds a node with specified data to the end of list
	* @param data - data of node to be added
	* @return true if node is added sucessfully
	*/

	public boolean add(E data) {

		//exception if data in new node is null
		if (data == null){
			throw new NullPointerException(); 
		}

		//initialize new Node and add to end of list
		
		Node newNode = new Node(data);

		newNode.next = this.tail; 
		newNode.prev = this.tail.prev; 
		this.tail.prev.next = newNode;
		this.tail.prev = newNode; 
		size++; 

		return true; 
	}

	/** 
	* sets data of the node at the specified index with given data
	* @param index - position of node to be set
	* @param data - data to set node 
	* @return prevStored- returns data of node before it was overwritten
	*/


	public E set(int index, E data) {

		//exception if index is greater than size or less than zero
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException(); 
		}
		
		if(this.isEmpty() == false){

			//exception if data in new node is null
			if (data == null){
				throw new NullPointerException(); 
			}
			//store data of old node
			E prevStored = this.get(index); 

			//set element using getNth to acess node 
			this.getNth(index).setElement(data);

			return prevStored; 
		}
		return data;

	}

	/** 
	* removes the node at the specified index 
	* @param index - position of node to be removed
	* @return data of node that was removed
	*/

	public E remove(int index) {

		//exception if index is greater than size or less than zero

		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException(); 
		}
		if(this.isEmpty() == false){
			Node nodeToRemove = this.getNth(index); 

			//if node is first node in list
			
			if(nodeToRemove == this.head.getNext()){
				this.head.next = nodeToRemove.getNext();
				nodeToRemove.next.prev = this.head; 
				size--; 
			}
			//if node is last node in list
			else if(nodeToRemove == this.tail.getPrev()){
				this.tail.prev = nodeToRemove.getPrev();
				nodeToRemove.prev.next = this.tail; 
				size--; 
			}

			//if node is only node in list
			else if(nodeToRemove == this.head.getNext() && nodeToRemove == this.tail.getPrev()){
				this.tail.prev = this.head;
				this.head.next = this.tail; 
				size--; 
			}
			//if node is in middle of list
			else{
				nodeToRemove.prev.next = nodeToRemove.getNext(); 
				nodeToRemove.next.prev = nodeToRemove.getPrev(); 
				size--; 
			}


			return nodeToRemove.getElement();
		}
		return null; 
	}

	/** 
	* clears the list except for dummy nodes
	*/
	public void clear() {
		int emptySize = 0; 

		//points sentinel nodes to each other
		this.head.next = this.tail;
		this.tail.prev = this.head; 

		//set size to 0
		size = emptySize; 
	}

	/** 
	* determines if list is empty
	* @return true if size is 0, false otherwise
	*/

	public boolean isEmpty() {

		return this.size == 0;  
	}

	/** 
	* helper method that returns node at specified index
	* @param index index of node to get 
	* @return node at index
	*/

	protected Node getNth(int index) {
		//exception if index is greater than size or less than zero
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException(); 
		}

		Node current = this.head.next; 
		int count = 0; 

		//iterates through nodes until index is reached

		while (current.getElement() != null) {
			if (count == index){
				return (current);
			}
			count++;
			current = current.getNext();
		}
		return (Node) null; 
	}
}