package domain.card;

import java.util.Objects;

public class Card {

	private final Denomination denomination;
	private final Suit suit;

	public Card(Denomination denomination, Suit suit) {
		this.denomination = denomination;
		this.suit = suit;
	}

	public int getPoint() {
		return denomination.getPoint();
	}

	public boolean isAce() {
		return denomination.isAce();
	}

	public String getDenomination() {
		return denomination.getDenomination();
	}

	public String getSuit() {
		return suit.getSuit();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Card))
			return false;
		Card card = (Card)o;
		return denomination == card.denomination && suit == card.suit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(denomination, suit);
	}
}
