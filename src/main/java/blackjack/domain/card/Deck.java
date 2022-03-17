package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck implements DrawStrategy {

	private static final String EMPTY_CARD_ERROR = "남은 카드가 존재하지 않습니다.";

	private final Deque<Card> deck = new ArrayDeque<>();

	public Deck(List<Card> deck) {
		List<Card> cards = new ArrayList<>(deck);
		Collections.shuffle(cards);
		this.deck.addAll(cards);
	}

	@Override
	public Card draw() {
		validateEmptyDeck();
		return deck.pop();
	}

	private void validateEmptyDeck() {
		if (deck.isEmpty()) {
			throw new IllegalStateException(EMPTY_CARD_ERROR);
		}
	}

	public int getSize() {
		return deck.size();
	}
}
