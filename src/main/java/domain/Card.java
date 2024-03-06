package domain;

import java.util.Objects;

public class Card {
	private final Suit suit;
	private final Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public int getScore() {
		return rank.getScore();
	}

	public String getSuitName() {
		return suit.getName();
	}

	public String getRankName() {
		return rank.getName();
	}

	public boolean isRank(Rank rank) {
		return this.rank == rank;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Card card = (Card)o;
		return suit == card.suit && rank == card.rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(suit, rank);
	}
}
