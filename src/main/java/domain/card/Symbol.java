package domain.card;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

public enum Symbol {
	ACE(1, "A", userScore -> userScore <= 11 && userScore + 11 <= 21, () -> 1 + 10),
	TWO(2, "2"),
	THREE(3, "3"),
	FOUR(4, "4"),
	FIVE(5, "5"),
	SIX(6, "6"),
	SEVEN(7, "7"),
	EIGHT(8, "8"),
	NINE(9, "9"),
	TEN(10, "10"),
	JACK(10, "J"),
	QUEEN(10, "Q"),
	KING(10, "K");

	private final int score;
	private final String name;
	private Predicate<Integer> scoreJudge;
	private Supplier<Integer> scoreConverter;

	Symbol(int score, String name) {
		this(score, name, userScore -> true, () -> score);
	}

	Symbol(int score, String name, Predicate<Integer> scoreJudge, Supplier<Integer> scoreConverter) {
		this.score = score;
		this.name = name;
		this.scoreJudge = scoreJudge;
		this.scoreConverter = scoreConverter;
	}

	public int getConvertedScoreByJudge(int userScore) {
		return Arrays.stream(Symbol.values())
				.filter(symbol -> symbol.equals(this))
				.filter(symbol -> symbol.scoreJudge.test(userScore))
				.mapToInt(symbol -> symbol.scoreConverter.get())
				.findFirst()
				.orElse(this.score);
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
}
