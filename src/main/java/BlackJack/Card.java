package BlackJack;

import java.util.ArrayList;

public class Card {
	

	private ArrayList<String> cards;
	
	public Card() {
		this.cards = new ArrayList<>();
	}
	
	public ArrayList<String> getCard() {
		return cards;
	}
	
	public void addCard(String c) {
		cards.add(c);
	}
	

}