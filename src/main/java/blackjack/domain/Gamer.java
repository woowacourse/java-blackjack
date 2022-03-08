package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;

public class Gamer {

	public static final int ZERO_POINT = 0;

	private final List<Card> cards;

	public Gamer() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}

	public boolean isOverThan(int number) {
		return getCardsNumberSum() > number;
	}

	public int getCardsNumberSum() {
		return cards.stream()
				.map(Card::getNumber)
				.reduce(ZERO_POINT, Integer::sum);
	}
}
