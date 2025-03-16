package card;

import java.util.Objects;

import game.GameScore;

public record Card(
	Rank rank,
	Suit suit
) {
	public boolean isMatchNumber(final Rank rank) {
		return Objects.equals(this.rank, rank);
	}

	public GameScore sumNumber(final GameScore gameScore) {
		return rank.sum(gameScore);
	}
}
