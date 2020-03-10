package domain.Gamer;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Gamer {
	private String name;
	protected final List<Card> cards = new ArrayList<>();

	public Gamer(String name, List<Card> cards) {
		this.name = name;
		initCard(cards);
	}

	private void initCard(List<Card> cards) {
		this.cards.addAll(cards);
	}

	protected void addCard(Card card) {
		cards.add(card);
	}

	public abstract boolean isDrawable();

	@Override
	public String toString() {
		return name + " : " + cards.stream()
			.map(Card::toString)
			.collect(Collectors.joining(","));
	}
}
