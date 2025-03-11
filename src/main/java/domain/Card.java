package domain;

import java.util.Objects;

public record Card(
	Rank rank,
	Suit suit
) {
	public boolean isMatchNumber(final Rank rank) {
		return Objects.equals(this.rank, rank);
	}

	public int sumNumber(final int score) {
		return rank.sum(score);
	}
}
