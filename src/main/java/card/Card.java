package card;

import java.util.Objects;

import value.Score;

public record Card(
	Rank rank,
	Suit suit
) {
	public boolean isMatchNumber(final Rank rank) {
		return Objects.equals(this.rank, rank);
	}

	public Score sumNumber(final Score score) {
		return rank.sum(score);
	}
}
