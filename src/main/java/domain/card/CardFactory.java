package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
	private static final List<Card> cards = new ArrayList<>();

	private CardFactory() {
	}

	static {
		for (Type type : Type.values()) {
			for (Symbol symbol : Symbol.values()) {
				cards.add(new Card(symbol, type));
			}
		}
	}

	public static List<Card> create() {
		return Collections.unmodifiableList(cards);
	}
}
