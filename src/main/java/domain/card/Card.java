package domain.card;

import java.util.Objects;

public class Card {

	private final Suit suit;
	private final Denomination denomination;

	public Card(final Suit suit, final Denomination denomination) {
		this.suit = suit;
		this.denomination = denomination;
	}

	public boolean isMatch(final Denomination denomination) {
		return this.denomination.equals(denomination);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Card card = (Card)o;
		return suit == card.suit && denomination == card.denomination;
	}

	@Override
	public int hashCode() {
		return Objects.hash(suit, denomination);
	}

	public Suit getSuit() {
		return suit;
	}

	public Denomination getDenomination() {
		return denomination;
	}

	public int getNumber() {
		return denomination.getNumber();
	}
}
