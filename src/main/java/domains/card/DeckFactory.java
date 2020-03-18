package domains.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DeckFactory {
    static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            createByType(cards, symbol);
        }
        Collections.shuffle(cards);
        return cards;
    }

	private static void createByType(List<Card> cards, Symbol symbol) {
		for (Type type : Type.values()) {
			cards.add(new Card(symbol, type));
		}
	}
}