package com.jaybaffoni;

public class Stack <T>{
	
	private SinglyLinkedList <T> theStack = new SinglyLinkedList <T>();
	
	public Stack() {
		
	}
	
	public Stack(T value) {
		push(value);
	}
	
	public boolean isEmpty() {
		return theStack.isEmpty();
	}
	
	public void clear() {
		theStack.clear();
	}
	
	public void push(T value) {
		theStack.insertAtHead(value);
	}
	
	public T pop() {
		if(theStack.isEmpty()) {
			// TODO throw error
		}
		return theStack.removeFirst();
	}
	
	public T peek() {
		if(theStack.isEmpty()) {
			// TODO throw error
		}
		return theStack.seeFirst();
	}
	
	public int size() {
		return theStack.size();
	}

}
