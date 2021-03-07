package blakcjack.domain.card;

import blakcjack.domain.shufflestrategy.ShuffleStrategy;

import java.util.List;
import java.util.Stack;

import static blakcjack.domain.card.EmptyDeckException.EMPTY_DECK_ERROR;

public class Deck {
	private final Stack<Card> cards = new Stack<>();

	public Deck(final ShuffleStrategy shuffleStrategy) {
		generateCards();
		shuffleStrategy.shuffle(cards);
	}

	public Deck(List<Card> cards) {
		this.cards.addAll(cards);
	}

	protected void generateCards() {
		for (final CardSymbol cardSymbol : CardSymbol.values()) {
			for (final CardNumber cardNumber : CardNumber.values()) {
				cards.add(Card.of(cardSymbol, cardNumber));
			}
		}
	}

	public Card drawCard() {
		if (cards.empty()) {
			throw new EmptyDeckException(EMPTY_DECK_ERROR);
		}
		return cards.pop();
	}
}
