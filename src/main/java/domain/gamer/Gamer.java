package domain.gamer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public abstract class Gamer {
	private String name;
	private final List<Card> cards = new ArrayList<>();
	private Result gameResult;

	public Gamer(String name) {
		this.name = name;
		// initCard(cards);
	}

	public void addCard(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public abstract boolean isDrawable();

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return cards;
	}

	@Override
	public String toString() {
		return name + " : " + cards.stream()
			.map(Card::toString)
			.collect(Collectors.joining(", "));
	}
}
