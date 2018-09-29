package com.jaybaffoni;

public class SinglyLinkedList <T> {
	
	private int size = 0;
	private Node <T> head = null;
	private Node <T> tail = null;
	
	private class Node <T> {
		
		Node <T> next;
		T value;
		
		public Node(T value) {
			
			this.value = value;
			this.next = null;
			
		}
		
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public void insert(T value) {
		
		Node <T> inserted = new Node <T> (value);
		
		if(isEmpty()) {
			head = inserted;
			tail = inserted;
		} else {
			tail.next = inserted;
			tail = inserted;
		}
		
		size++;
		
	}
	
	public void insertAtHead(T value) {
		
		Node <T> inserted = new Node <T> (value);
		
		if(head == null) {
			head = inserted;
			tail = inserted;
		} else {
			inserted.next = head;
			head = inserted;
		}
		
		size++;
		
	}
	
	public void insertAtKth(T value, int k) {
		
		if(k < 0 || k >= size) {
			// TODO throw error
		}
		
		if(k == 0) {
			insertAtHead(value);
		}
		
		if(k == size) {
			insert(value);
		}
		
		Node <T> inserted = new Node <T> (value);
		
		Node <T> current = head;
		int traveled = 0;
		while(traveled < k - 1) {
			current = current.next;
			if(current == null) {
				// TODO throw error
			}
			traveled++;
		}
		inserted.next = current.next;
		current.next = inserted;
		
		size++;
		
	}
	
	public String toString() {
		if(isEmpty()) {
			return "[]";
		}
		
		String toPrint = "";
		
		Node <T> current = head;
		while(current != null) {
			toPrint = toPrint + current.value.toString() + ", ";
			current = current.next;
		}
		
		return toPrint;
	}
	

	public T seeFirst() {
		
		if(isEmpty()) {
			// TODO throw error
		}
		return head.value;
		
	}
	
	public T seeLast() {
		
		if(isEmpty()) {
			// TODO throw error
		}
		return tail.value;
		
	}
	
	public T removeFirst() {
		
		if(isEmpty()) {
			// TODO throw error
		}
		
		T toReturn = head.value;
		head = head.next;
		size--;
		
		if(isEmpty()){
			tail = null;
		}
		
		return toReturn;
		
	}
	
	public T removeLast() {
		
		if(isEmpty()) {
			// TODO throw error
		}
		
		T toReturn = tail.value;
		Node <T> current = head;
		while(current.next != tail){
			current = current.next;
		}
		current.next = null;
		tail = current;
		size--;
		if(isEmpty()){
			head = null;
		}
		return toReturn;
		
	}
	
	private T remove(Node <T> node) {
		
		if(isEmpty()) {
			// TODO throw error
		}
		
		if(node.next == null) {
			return removeLast();
		}
		if(node == head) {
			return removeFirst();
		}
		
		T toReturn = node.value;
		Node <T> current = head;
		while(current.next != node){
			current = current.next;
		}
		current.next = node.next;
		size--;
		return toReturn;
		
	}
	
	public T valueAt(int index) {
		if(index < 0 || index >= size) {
			// TODO throw error
		}
		
		Node <T> current = head;
		for(int count = 0; count < index; count++) {
			current = current.next;
		}
		return current.value;
	}
	
	public T removeFromIndex(int index) {
		
		if(index < 0 || index >= size) {
			// TODO throw error
		}
		
		Node <T> current = head;
		for(int count = 0; count < index; count++) {
			current = current.next;
		}
		return remove(current);
		
	}
	
	public boolean remove(T value) {
		
		if(isEmpty()) {
			// TODO throw error
		}
		
		Node <T> current = head;
		while(current != null) {
			if(current.value == value) {
				remove(current);
				return true;
			}
			current = current.next;
		}
		return false;
		
	}
	
	public int indexOf(T value) {
		
		if(isEmpty()) {
			// TODO throw error
		}
		
		int count = 0;
		Node <T> current = head;
		while(current != null) {
			if(current.value == value) {
				return count;
			}
			current = current.next;
			count++;
		}
		return -1;
		
	}
	
	public boolean contains(T value) {
		
		if(isEmpty()) {
			// TODO throw error
		}
		
		Node <T> current = head;
		while(current != null) {
			if(current.value == value) {
				return true;
			}
			current = current.next;
		}
		return false;
		
	}
	
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
}
