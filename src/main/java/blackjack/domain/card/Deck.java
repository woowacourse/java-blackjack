package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.Collectors;

public class Deck {

	public static final String EMPTY_DECK_ERROR = "드로우 가능한 카드가 더이상 없습니다.";
	private final Queue<Card> cards;

	public Deck() {
		cards = new LinkedList<>(createDeck());
	}

	private List<Card> createDeck() {
		List<Suit> suits = Arrays.asList(Suit.values());
		List<Card> newCards = suits.stream()
			.map(Denomination::makeSuitSet)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
		Collections.shuffle(newCards);
		return newCards;
	}

	public Card draw() {
		if (cards.isEmpty()) {
			throw new NoSuchElementException(EMPTY_DECK_ERROR);
		}
		return cards.poll();
	}
}
