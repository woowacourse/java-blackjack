package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffledDeckFactory implements DeckFactory {
	@Override
	public Deck create() {
		List<Card> deck = new ArrayList<>();
		putAllCards(deck);
		Collections.shuffle(deck);

		return Deck.of(deck);
	}

	/**
	 * 추상화 수준이 같아서 depth 2로 해도 되지 않을까요 ㅎㅎ;
	 */
	private void putAllCards(List<Card> deck) {
		for (Symbol symbol : Symbol.values()) {
			for (Type type : Type.values()) {
				deck.add(Card.of(symbol, type));
			}
		}
	}
}
