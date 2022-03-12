package blackjack.domain.card;

import java.util.Objects;

public class Card {
	private final Denomination denomination;
	private final Suit suit;

	public Card(Denomination denomination, Suit suit) {
		this.denomination = denomination;
		this.suit = suit;
	}

	public int getNumber() {
		return denomination.getValue();
	}

	public String getType() {
		return suit.getValue();
	}

	public int getScore() {
		return this.denomination.getValue();
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
		Card card = (Card) o;
		return denomination == card.denomination && suit == card.suit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(denomination, suit);
	}
}
