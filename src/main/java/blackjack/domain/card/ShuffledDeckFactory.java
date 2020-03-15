package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShuffledDeckFactory implements DeckFactory {
	@Override
	public Deck create() {
		List<Card> deck = createAllCards();
		Collections.shuffle(deck);

		return Deck.of(deck);
	}

	private List<Card> createAllCards() {
		List<Card> deck = new ArrayList<>();
		for (Symbol symbol : Symbol.values()) {
			Arrays.stream(Type.values())
					.forEach(type -> deck.add(Card.of(symbol, type)));
		}

		return deck;
	}
}
