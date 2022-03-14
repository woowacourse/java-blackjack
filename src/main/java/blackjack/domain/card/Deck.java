package blackjack.domain.card;

import static blackjack.domain.exceptionMessages.CardExceptionMessage.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
	private final Deque<Card> deck;

	public Deck() {
		List<Card> cards = setUpCards();
		Collections.shuffle(cards);
		deck = new ArrayDeque<>(cards);
	}

	private List<Card> setUpCards() {
		return Stream.of(Denomination.values())
			.flatMap(this::setUpCardSuitByDenomination)
			.collect(Collectors.toList());
	}

	private Stream<Card> setUpCardSuitByDenomination(final Denomination denomination) {
		return Stream.of(Suit.values())
			.map(suit -> new Card(denomination, suit));
	}

	public Card distributeCard() {
		final int cardSize = deck.size();
		if (cardSize <= 0) {
			throw new IllegalStateException(EMPTY_DECK_EXCEPTION.getMessage());
		}
		return deck.removeLast();
	}

	public Deque<Card> getCards() {
		return this.deck;
	}
}
