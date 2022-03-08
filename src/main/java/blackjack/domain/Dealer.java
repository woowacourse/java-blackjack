package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;

public class Dealer implements CardManager{

	private final List<Card> cards;

	public Dealer() {
		cards = new ArrayList<>();
	}

	@Override
	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}

	@Override
	public boolean isOverThan(int number) {
		int sum = cards.stream()
			.map(card -> card.getNumber())
			.reduce(0, Integer::sum);
		return sum > number;
	}
}
