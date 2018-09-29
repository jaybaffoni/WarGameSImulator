package com.jaybaffoni;

public class Card implements Comparable<Card>{

	int value;
	char suit;
	String symbol;
	
	public Card(int value, char suit, String symbol) {
		this.value = value;
		this.suit = suit;
		this.symbol = symbol;
	}
	
	public int getValue() {
		return value;
	}
	
	public char getSuit() {
		return suit;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	@Override
	public int compareTo(Card o) {
		return this.value - o.getValue();
	}


}
