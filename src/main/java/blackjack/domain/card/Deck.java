package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
	public static final String EMPTY_DECK_EXCEPTION = "덱의 카드가 다 소진되었습니다.";

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
			throw new IllegalStateException(EMPTY_DECK_EXCEPTION);
		}
		return deck.removeLast();
	}

	public Deque<Card> getCards() {
		return new ArrayDeque<>(this.deck);
	}
}
