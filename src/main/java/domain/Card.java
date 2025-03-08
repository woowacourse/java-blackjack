package domain;

import java.util.Objects;

import constant.Suit;

public record Card(
	Rank rank,
	Suit suit
) {
	public boolean isMatchNumber(final Rank cardNumber) {
		return Objects.equals(rank, cardNumber);
	}

	public int sumNumber(final int score) {
		return rank.sum(score);
	}
}
