package GroupProject1_WarCardGame;

import java.util.ArrayList;
import java.util.List;

public class Card {
	String suit;
	String value;
	int num;
	/**
	 * @param suit
	 * @param value
	 */
	public Card(String suit, String value) {
		super();
		this.suit = suit;
		this.value = value;
	}

	@Override
	public String toString() {
		return suit+value;
	}
	
	// Methods
	public int getNumber() {
		if(this.value.equals("A")) return 14;
		else if(this.value.equals("K")) return 13;
		else if(this.value.equals("Q")) return 12;
		else if(this.value.equals("J")) return 11;
		else return Integer.parseInt(this.value);
	}
	
	
	
	/*
	// the array of all the cards
	String[] huase = {"♦️","♠️","♣️","♥️"};
	String[] number = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	List<String> deck = new ArrayList<>();
	for (String suit : huase) {
		for (String rank : number) {
			deck.add(suit + rank);
	    }
	}
	*/
}
