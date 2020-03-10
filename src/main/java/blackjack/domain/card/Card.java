package blackjack.domain.card;

public class Card {
	private final Symbol symbol;
	private final Type type;

	public Card(Symbol symbol, Type type) {
		this.symbol = symbol;
		this.type = type;
	}
}
