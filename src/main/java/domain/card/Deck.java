package domain.card;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.card.deckstrategy.GenerationDeckStrategy;

public class Deck {

	private static final String DECK_SIZE_ZERO_MESSAGE = "[Error] 카드가 없습니다.";
	private static final int INIT_SIZE = 2;

	private final Queue<Card> cards;

	private Deck(Queue<Card> cards) {
		this.cards = cards;
	}

	public static Deck from(GenerationDeckStrategy strategy) {
		Queue<Card> cards = strategy.generateCardsForBlackJack();
		return new Deck(cards);
	}

	public List<Card> generateInitCards() {
		List<Card> cards = IntStream.range(0, INIT_SIZE)
			.mapToObj(i -> draw())
			.collect(Collectors.toList());
		return cards;
	}

	public Card draw() {
		if (cards.size() <= 0) {
			throw new IllegalStateException(DECK_SIZE_ZERO_MESSAGE);
		}

		Card card = cards.poll();
		return card;
	}
}
