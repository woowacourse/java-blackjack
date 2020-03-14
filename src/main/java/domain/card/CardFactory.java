package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
	private static List<Card> cards = new ArrayList<>();

	static {
		Symbol[] symbols = Symbol.values();
		for (Symbol symbol : symbols) {
			createCardsByType(symbol);
		}
	}

	private CardFactory() {
	}

	private static void createCardsByType(Symbol symbol) {
		for (Type type : Type.values()) {
			cards.add(new Card(type, symbol));
		}
	}

	public static List<Card> getInstance() {
		return Collections.unmodifiableList(cards);
	}
}
