package blakcjack.domain.card;

import blakcjack.domain.shufflestrategy.ShuffleStrategy;

import java.util.List;
import java.util.Stack;

public class Deck {
	private final Stack<Card> cards = new Stack<>();

	public Deck(final ShuffleStrategy shuffleStrategy) {
		generateCards();
		shuffleStrategy.shuffle(cards);
	}

	public Deck(List<Card> cards) {
		this.cards.addAll(cards);
	}

	private void generateCards() {
		for (final CardSymbol cardSymbol : CardSymbol.values()) {
			for (final CardNumber cardNumber : CardNumber.values()) {
				cards.add(Card.of(cardSymbol, cardNumber));
			}
		}
	}

	public Card pop() {
		if (cards.empty()) {
			throw new EmptyDeckException();
		}
		return cards.pop();
	}
}
