package domain.card;

import java.util.Objects;

public class Card {

	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getPoint() {
		return rank.getPoint();
	}

	public boolean isAce() {
		return rank.isAce();
	}

	public String getCardInfo() {
		return rank.getRank() + suit.getSuit();
	}

	public static Card copyOf(Card card) {
		return new Card(card.rank, card.suit);
	}

	@Override
	public String toString() {
		return rank.getRank() + suit.getSuit();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Card))
			return false;
		Card card = (Card)o;
		return rank == card.rank && suit == card.suit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}
}
