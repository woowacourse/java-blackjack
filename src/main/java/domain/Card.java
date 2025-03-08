package domain;

import java.util.Objects;

import constant.Emblem;

public record Card(
	Rank number,
	Emblem emblem
) {
	public boolean isMatchNumber(final Rank cardNumber) {
		return Objects.equals(number, cardNumber);
	}

	public int sumNumber(final int score) {
		return number.sum(score);
	}
}
