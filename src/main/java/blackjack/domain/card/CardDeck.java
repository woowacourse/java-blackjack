package blackjack.domain.card;

public class CardDeck {
	private final Cards deck;

	private CardDeck(Cards cards) {
		this.deck = cards;
	}

	public static CardDeck create() {
		Cards cards = new Cards();
		for (CardSymbol symbol : CardSymbol.values()) {
			createWith(cards, symbol);
		}

		return new CardDeck(cards);
	}

	private static void createWith(Cards cards, CardSymbol symbol) {
		for (CardValue value : CardValue.values()) {
			cards.addCard(new Card(symbol, value));
		}
	}

	public Card pick() {
		Card pickedCard = deck.pickRandomCard();
		deck.remove(pickedCard);
		return pickedCard;
	}
}
