package com.jaybaffoni;

import java.util.ArrayList;
import java.util.Collections;

public class CardGame {
	
	static char[] suits = {'C', 'S', 'H', 'D'};
	static String[] symbols = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	
	static ArrayList<Card> theDeck = new ArrayList<Card>();
	static ArrayList<Card> deck1 = new ArrayList<Card>();
	static ArrayList<Card> deck2 = new ArrayList<Card>();
	static ArrayList<Card> discard1 = new ArrayList<Card>();
	static ArrayList<Card> discard2 = new ArrayList<Card>();
	
	static int turn = 0;
	static int totalTurns = 0;
	static int p1wins = 0;
	static int p2wins = 0;
	static int gamesPlayed = 0;
	static int betterValueWins = 0;
	static int betterValuePlayer = 0;
	static int valueDifference = 0;
	static int totalValueMargin = 0;
	
	static int[] gamesPerDifference = new int[144];
	static int[] winsPerDifference = new int[144];
	//static double[] percentArray = new double[144];

	public static void main(String[] args) {
		
		
		fillDeck();
		
		while(true) {
			initializeGame();
			int player1count = deck1.size() + discard1.size();
			int player2count = deck2.size() + discard2.size();
			while(player1count > 0 && player2count > 0) {
				nextTurn();
				player1count = deck1.size() + discard1.size();
				player2count = deck2.size() + discard2.size();
			}
			
			if(player1count == 0) {
				p2wins++;
				if(betterValuePlayer == 2) {
					betterValueWins++;
					totalValueMargin += valueDifference;
				} else {
					totalValueMargin -= valueDifference;
				}
			}
			else {
				p1wins++;
				if(betterValuePlayer == 1) {
					betterValueWins++;
					totalValueMargin += valueDifference;
				} else {
					totalValueMargin -= valueDifference;
				}
			}
				
			
			totalTurns += turn;
			int avgValueMargin = totalValueMargin / gamesPlayed;
			int bvPercent = (betterValueWins * 100) / gamesPlayed;
			//System.out.println("Games: " + gamesPlayed + "\tP1 Wins: " + p1wins + "\tP2 Wins: " + p2wins + "\tAvg Turns: " + (totalTurns/gamesPlayed)
			//		+ "\tBV Win %: " + bvPercent + "\tAVG Margin: " + avgValueMargin);
			
			
		}
		
		
	}
	
	public static void initializeGame() {
		Collections.shuffle(theDeck);
		turn = 0;
		deck1.clear();
		deck2.clear();
		discard1.clear();
		discard2.clear();
		boolean player1 = true;
		for(Card c: theDeck) {
			if(player1)
				deck1.add(c);
			else
				deck2.add(c);
			player1 = !player1;
		}
		Collections.shuffle(deck1);
		Collections.shuffle(deck2);
		int p1Value = getDeckValue(deck1, discard1);
		int p2Value = getDeckValue(deck2, discard2);
		if(p1Value == p2Value) {
			initializeGame();
		}
		if(p1Value > p2Value)
			betterValuePlayer = 1;
		else
			betterValuePlayer = 2;
		
		valueDifference = Math.abs(p1Value - p2Value);
		gamesPlayed++;
		System.out.println("\nP1 Value: " + p1Value + "\tP2 Value: " + p2Value);
	}
	
	public static void nextTurn() {
		//System.out.println("\nTurn: " + turn);
		
		ArrayList<Card> atStake = new ArrayList<Card>();
		Card player1 = deck1.remove(0);
		Card player2 = deck2.remove(0);
		atStake.add(player1);
		atStake.add(player2);
		
		while(player1.compareTo(player2) == 0) {
			//System.out.println("***WAR***");
			for(int i = 0; i < 4; i++) {
				checkFill();
				if(!deck1.isEmpty()) {
					player1 = deck1.remove(0);
					atStake.add(player1);
				}
				if(!deck2.isEmpty()) {
					player2 = deck2.remove(0);
					atStake.add(player2);
				}
			}
		}
		
		if(player1.compareTo(player2) > 0) {
			discard1.addAll(atStake);
		} else if(player2.compareTo(player1) > 0) {
			discard2.addAll(atStake);
		} 
		
		checkFill();
		turn++;
		//printGameState();
	}
	
	public static void checkFill() {
		if(deck1.isEmpty()) {
			deck1.addAll(discard1);
			Collections.shuffle(deck1);
			discard1.clear();
		}
		if(deck2.isEmpty()) {
			deck2.addAll(discard2);
			Collections.shuffle(deck2);
			discard2.clear();
		}
	}
	
	public static void printGameState() {
		
		System.out.println("Player 1" + "\tCards: " + (deck1.size() + discard1.size()) + "\tValue: " + getDeckValue(deck1, discard1));
		System.out.println("Player 2" + "\tCards: " + (deck2.size() + discard2.size()) + "\tValue: " + getDeckValue(deck2, discard2));
	}
	
	public static int getDeckValue(ArrayList<Card> deck, ArrayList<Card> discard) {
		int toReturn = 0;
		for(Card c: deck) {
			toReturn += c.getValue();
		}
		for(Card c: discard) {
			toReturn += c.getValue();
		}
		return toReturn;
	}
	
	public static void fillDeck() {
		theDeck.clear();
		for(int v = 0; v < 13; v++) {
			for(int s = 0; s < 4; s++) {
				theDeck.add(new Card(v, suits[s], symbols[v]));
			}
		}
	}
	
	public static void printDeck(ArrayList<Card> deck) {
		String toPrint = "";
		for(Card c: deck) {
			toPrint += c.symbol + c.suit + ", ";
		}
		System.out.println(toPrint);
		System.out.println(deck.size());
	}

}
