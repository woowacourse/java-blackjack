package blackjack.domain.card;

import java.util.Objects;

import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;

public class Card {

	private final Suit suit;
	private final Denomination denomination;

	public Card(final Suit suit, final Denomination denomination) {
		this.suit = suit;
		this.denomination = denomination;
	}

	public boolean isAce() {
		return denomination == Denomination.ACE;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Card card = (Card)o;
		return suit == card.suit && denomination == card.denomination;
	}

	@Override
	public int hashCode() {
		return Objects.hash(suit, denomination);
	}

	public Denomination getDenomination() {
		return denomination;
	}

	public String getName() {
		return denomination.getName() + suit.getName();
	}
}
