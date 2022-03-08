package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;

public class Dealer implements CardManager {

	public static final int ZERO_POINT = 0;
	private final List<Card> cards;

	public Dealer() {
		cards = new ArrayList<>();
	}

	@Override
	public void addCard(Card card) {
		cards.add(card);
	}

	@Override
	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}

	@Override
	public boolean isOverThan(int number) {
		return getCardsNumberSum() > number;
	}

	@Override
	public int getCardsNumberSum() {
		return cards.stream()
			.map(Card::getNumber)
			.reduce(ZERO_POINT, Integer::sum);
	}
}
