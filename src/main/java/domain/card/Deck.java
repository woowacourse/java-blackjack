package domain.card;

import java.util.List;

import domain.card.deckstrategy.GenerationDeckStrategy;

public class Deck {

	private static final int FRONT_CARD_INDEX = 0;
	public static final String DECK_SIZE_ZERO_MESSAGE = "[Error] 카드가 없습니다.";

	private final List<Card> cards;

	private Deck(List<Card> cards) {
		this.cards = cards;
	}

	public static Deck from(GenerationDeckStrategy strategy) {
		List<Card> cards = strategy.generateCardsForBlackJack();
		return new Deck(cards);
	}

	public Card draw() {
		if (cards.size() <= 0) {
			throw new IllegalStateException(DECK_SIZE_ZERO_MESSAGE);
		}
		
		Card card = cards.get(FRONT_CARD_INDEX);
		cards.remove(FRONT_CARD_INDEX);
		return card;
	}
}
