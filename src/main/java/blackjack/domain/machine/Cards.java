package blackjack.domain.machine;

import java.util.HashSet;
import java.util.Set;

import blackjack.domain.strategy.NumberGenerator;

public class Cards {
	private Set<Card> PickedCards = new HashSet<>();

	public Card pickCard(NumberGenerator numberGenerator) {
		int index = numberGenerator.generateNumber();
		Card card = Card.of(index);

		while (PickedCards.contains(card)) {
			index = numberGenerator.generateNumber();
			card = Card.of(index);
		}

		PickedCards.add(card);

		return card;
	}
}
