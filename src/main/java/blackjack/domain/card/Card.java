package blackjack.domain.card;

public final class Card {
	private final CardSymbol symbol;
	private final CardNumber number;

	public Card(CardSymbol symbol, CardNumber number) {
		this.symbol = symbol;
		this.number = number;
	}

	boolean isAce() {
		return number.equals(CardNumber.ACE);
	}

	String getName() {
		return number.getName() + symbol.getName();
	}

	int getNumber() {
		return number.getNumber();
	}
}
