package domain.card;

import java.util.Arrays;

public enum Symbol {
	ACE("1", 1),
	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 10),
	QUEEN("Q", 10),
	KING("K", 10);

	private static final int ACE_ADDITIONAL_SCORE = 10;
	private static final String ILLEGAL_SCORE_VALUE_EXCEPTION_MESSAGE = "올바른 인자가 아닙니다.";

	private final String name;
	private final int score;

	Symbol(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public static Symbol of(Integer num) {
		return Arrays.stream(values())
			.filter(val -> val.score == num)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_SCORE_VALUE_EXCEPTION_MESSAGE));
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public boolean isAce() {
		return this == ACE;
	}

	public int calculateScore(int totalScore, int blackjackScore) {
		if (!isAce()) {
			return getScore();
		}
		if (totalScore + getScore() + ACE_ADDITIONAL_SCORE <= blackjackScore) {
			return getScore() + ACE_ADDITIONAL_SCORE;
		}
		return getScore();
	}
}
