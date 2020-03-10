package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Cards;

public class Player {
	private final String name;
	private final Cards cards;

	public Player(String name) {
		this.name = name;
		this.cards = new Cards(new ArrayList<>());
	}

	public void addCards(ArrayList<Card> cards) {
		this.cards.addAll(cards);
	}

	public boolean isBlackjack() {
		return cards.isBlackjack();
	}
}
