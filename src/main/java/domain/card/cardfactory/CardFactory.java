package domain.card.cardfactory;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {
	public static List<Card> create() {
		List<Card> cards = new ArrayList<>();
		for (Symbol symbol : Symbol.values()) {
			makeCardByShape(cards, symbol);
		}
		return cards;
	}

	private static void makeCardByShape(List<Card> cards, Symbol symbol) {
		for (Shape shape : Shape.values()) {
			cards.add(new Card(symbol, shape));
		}
	}
}
