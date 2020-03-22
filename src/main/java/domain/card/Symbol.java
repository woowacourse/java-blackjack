package domain.card;

import java.util.function.Predicate;
import java.util.function.Supplier;

public enum Symbol {
	ACE(1, "A", userScore -> userScore <= 11, () -> 10),
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
	private Predicate<Integer> promotableJudge;
	private Supplier<Integer> promotionScore;

	Symbol(int score, String name) {
		this(score, name, userScore -> false, () -> 0);
	}

	Symbol(int score, String name, Predicate<Integer> promotableJudge, Supplier<Integer> promotionScore) {
		this.score = score;
		this.name = name;
		this.promotableJudge = promotableJudge;
		this.promotionScore = promotionScore;
	}

	public boolean getPromotableJudge(int userScore) {
		return promotableJudge.test(userScore);
	}

	public int getPromotionScore() {
		return promotionScore.get();
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
}
