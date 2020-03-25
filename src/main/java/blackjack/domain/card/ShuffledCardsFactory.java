package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShuffledCardsFactory implements CardsFactory {

	@Override
	public List<Card> create() {
		List<Card> cards = createCards();
		Collections.shuffle(cards);

		return cards;
	}

	private List<Card> createCards() {
		List<Card> cards = new ArrayList<>();
		for (Symbol symbol : Symbol.values()) {
			attachEachTypeToSymbolAndPutItInCards(cards, symbol);
		}

		return cards;
	}

	private void attachEachTypeToSymbolAndPutItInCards(List<Card> deck, Symbol symbol) {
		for (Type type : Type.values()) {
			deck.add(Card.of(symbol, type));
		}
	}
}
