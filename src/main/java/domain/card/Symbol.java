package domain.card;

import java.util.Arrays;

public enum Symbol {
	ACE("1", 1, 10),
	TWO("2", 2, 0),
	THREE("3", 3, 0),
	FOUR("4", 4, 0),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 10),
	QUEEN("Q", 10),
	KING("K", 10);

	private static final String ILLEGAL_SCORE_VALUE_EXCEPTION_MESSAGE = "올바른 인자가 아닙니다.";

	private final String name;
	private final int defaultScore;
	private final int bonusScore;

	Symbol(String name, int defaultScore) {
		this(name, defaultScore, 0);
	}

	Symbol(String name, int defaultScore, int bonusScore) {
		this.name = name;
		this.defaultScore = defaultScore;
		this.bonusScore = bonusScore;
	}

	public static Symbol of(Integer num) {
		return Arrays.stream(values())
			.filter(val -> val.defaultScore == num)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_SCORE_VALUE_EXCEPTION_MESSAGE));
	}

	public String getName() {
		return name;
	}

	public int getDefaultScore() {
		return defaultScore;
	}

	public int getBonusScore() {
		return bonusScore;
	}

	public int calculateBonusScore(int score, int comparingScore) {
		if (score + bonusScore <= comparingScore) {
			return bonusScore;
		}
		return 0;
	}
}
