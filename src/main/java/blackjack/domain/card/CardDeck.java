package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
	private final static List<Card> deck = new ArrayList<>();

	static {
		for (CardSymbol symbol : CardSymbol.values()) {
			generateCardWith(symbol);
		}
	}

	private static void generateCardWith(CardSymbol symbol) {
		for (CardValue value : CardValue.values()) {
			deck.addCard(new Card(symbol, value));
		}
	}

	public static Card pickCard() {
		Collections.shuffle(deck);
		Card pickedCard = deck.get(0);
		removeCard(pickedCard);
		return pickedCard;
	}

	private static void removeCard(Card card) {
		deck.remove(card);
	}
}
