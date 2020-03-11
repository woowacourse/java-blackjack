package factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Card;
import domain.Symbol;
import domain.Type;

public class CardFactory {
	public static List<Card> create() {
		List<Card> cards = new ArrayList<>();
		for (Symbol symbol : Symbol.values()) {
			createByType(symbol, cards);
		}
		return Collections.unmodifiableList(cards);
	}

	private static void createByType(Symbol symbol, List<Card> cards) {
		for (Type type : Type.values()) {
			cards.add(Card.of(type.getName(), symbol.getAlias()));
		}
	}
}
