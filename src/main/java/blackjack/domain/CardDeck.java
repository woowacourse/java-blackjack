package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
	private final static List<Card> deck = new ArrayList<>();

	static {
		for (CardType type : CardType.values()) {
			generateCardWith(type);
		}
	}

	private static void generateCardWith(CardType type) {
		for (CardValue value : CardValue.values()) {
			String key = value.getName().concat(type.getName());
			deck.add(new Card(key, value.getValue()));
		}
	}

	public Card pickCard() {
		Collections.shuffle(deck);
		Card pickedCard = deck.get(0);
		removeCard(pickedCard);
		return pickedCard;
	}

	private void removeCard(Card card) {
		deck.remove(card);
	}
}
