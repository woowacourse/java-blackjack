package blackjack.domain.card;

public class Card {
	private final CardSymbol symbol;
	private final CardValue value;

	public Card(CardSymbol symbol, CardValue value) {
		this.symbol = symbol;
		this.value = value;
	}

	public boolean isAce() {
		return value.equals(CardValue.ACE);
	}

	public String getName() {
		return value.getName() + symbol.getName();
	}

	public int getValue() {
		return value.getValue();
	}
}
