package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

	public static List<Card> create() {
		List<Card> cards = new ArrayList<>();
		for (Type type : Type.values()) {
			for (Symbol symbol : Symbol.values()) {
				cards.add(new Card(symbol, type));
			}
		}
		return Collections.unmodifiableList(cards);
	}
}
