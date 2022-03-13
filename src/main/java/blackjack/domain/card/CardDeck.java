package blackjack.domain.card;

public class CardDeck {
	private final static Cards deck = new Cards();

	static {
		for (CardSymbol symbol : CardSymbol.values()) {
			generateCardWith(symbol);
		}
	}

	private static void generateCardWith(CardSymbol symbol) {
		for (CardValue value : CardValue.values()) {
			deck.addCard(new Card(symbol, value));
		}
	}

	public static Card pick() {
		Card pickedCard = deck.pickRandomCard();
		deck.remove(pickedCard);
		return pickedCard;
	}
}
