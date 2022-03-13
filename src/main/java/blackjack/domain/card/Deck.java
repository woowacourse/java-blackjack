package blackjack.domain.card;

import static blackjack.domain.exceptionMessages.CardExceptionMessage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {
	private final List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		setUpCards();
		Collections.shuffle(cards);
	}

	private void setUpCards() {
		Stream.of(Denomination.values())
			.forEach(denomination -> setUpCardSuitByDenomination(denomination)
			);
	}

	private void setUpCardSuitByDenomination(final Denomination denomination) {
		Stream.of(Suit.values())
			.forEach(type -> addCardToDeck(denomination, type));
	}

	private void addCardToDeck(Denomination denomination, Suit type) {
		Card card = new Card(denomination, type);
		cards.add(card);
	}

	public Card distributeCard() {
		final int cardSize = cards.size();
		if (cardSize <= 0) {
			throw new IllegalStateException(EMPTY_DECK_EXCEPTION.getMessage());
		}
		return cards.remove(cards.size() - 1);
	}

	public List<Card> getCards() {
		return this.cards;
	}
}
