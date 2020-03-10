package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class Player {
	private final String name;
	private final List<Card> cards;

	public Player(String name) {
		this.name = name;
		this.cards = new ArrayList<>();
	}

	public void addCards(ArrayList<Card> cards) {
		this.cards.addAll(cards);
	}

	public boolean isBlackjack() {
		if (cards.size() == 2 && calculateScore() == 21) {
			return true;
		}
		return false;
	}

	private int calculateScore() {
		return cards.stream()
			.map(Card::getScore)
			.reduce(Integer::sum)
			.orElseThrow(() -> new IllegalArgumentException());

	}
}
