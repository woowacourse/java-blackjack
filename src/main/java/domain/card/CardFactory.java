package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
	private static final List<Card> CARDS = new ArrayList<>();

	static {
		for (Type type : Type.values()) {
			createCardsByType(type);
		}
	}

	private static void createCardsByType(Type type) {
		for (Symbol symbol : Symbol.values()) {
			CARDS.add(new Card(symbol, type));
		}
	}

	public static List<Card> create() {
		return Collections.unmodifiableList(CARDS);
	}

}
