package blakcjack.domain.card;

import java.util.Objects;

public class Card {
	private final CardSymbol cardSymbol;
	private final CardNumber cardNumber;

	private Card(final CardSymbol cardSymbol, final CardNumber cardNumber) {
		this.cardSymbol = cardSymbol;
		this.cardNumber = cardNumber;
	}

	public static Card of(final CardSymbol cardSymbol, final CardNumber cardNumber ) {
		return new Card(cardSymbol, cardNumber);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return cardSymbol == card.cardSymbol && cardNumber == card.cardNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardSymbol, cardNumber);
	}
}
