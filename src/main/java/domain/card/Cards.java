package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
	private static final List<Card> cards = new ArrayList<>();

	private Cards() {
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
