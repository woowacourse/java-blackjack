package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardFactory {
	private static final List<Card> CARDS = new ArrayList<>();

	static {
		Arrays.stream(Type.values())
			.forEach(type -> createCardsByType(type));
	}

	private static void createCardsByType(Type type) {
		Arrays.stream(Symbol.values())
			.forEach(symbol -> CARDS.add(new Card(symbol, type)));
	}

	public static List<Card> create() {
		return Collections.unmodifiableList(CARDS);
	}

}