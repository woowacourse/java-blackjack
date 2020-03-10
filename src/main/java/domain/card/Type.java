package domain.card;

import java.util.Arrays;

public enum Type {
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10);

	private final int score;

	Type(int score) {
		this.score = score;
	}

	public static Type of(Integer num) {
		return Arrays.stream(values())
			.filter(val -> val.score == num)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("올바른 인자가 아닙니다."));
	}

	public int getScore() {
		return this.score;
	}
}
