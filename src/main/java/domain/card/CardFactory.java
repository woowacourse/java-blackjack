package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
	private static List<Card> cards = new ArrayList<>();

	static {
		Type[] types = Type.values();
		for (Type type : types) {
			createCardsByType(type);
		}
	}

	private CardFactory() {
	}

	private static void createCardsByType(Type type) {
		for (Symbol symbol : Symbol.values()) {
			cards.add(new Card(symbol, type));
		}
	}

	public static List<Card> getInstance() {
		return Collections.unmodifiableList(cards);
	}
}
