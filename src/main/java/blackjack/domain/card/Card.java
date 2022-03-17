package blackjack.domain.card;

public class Card {
	private final CardSymbol symbol;
	private final CardNumber number;

	public Card(CardSymbol symbol, CardNumber number) {
		this.symbol = symbol;
		this.number = number;
	}

	public boolean isAce() {
		return number.equals(CardNumber.ACE);
	}

	public String getName() {
		return number.getName() + symbol.getName();
	}

	public int getNumber() {
		return number.getNumber();
	}
}
