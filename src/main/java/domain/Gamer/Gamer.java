package domain.Gamer;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Gamer {
	private String name;
	private final List<Card> cards = new ArrayList<>();
	private Result gameResult;

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

	public List<Card> getCards() {
		return cards;
	}

	@Override
	public String toString() {
		return name + " : " + cards.stream()
			.map(Card::toString)
			.collect(Collectors.joining(","));
	}
}
