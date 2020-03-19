package domain.card;

import java.util.function.Predicate;
import java.util.function.Supplier;

public enum Symbol {
	ACE(1, "A", score -> score <= 11, () -> 10),
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
	private final Predicate<Integer> promotionJudge;
	private final Supplier<Integer> bonusScore;

	Symbol(int score, String name) {
		this(score, name, i -> false, () -> 0);
	}

	Symbol(int score, String name, Predicate<Integer> promotionJudge, Supplier<Integer> bonusScore) {
		this.score = score;
		this.name = name;
		this.promotionJudge = promotionJudge;
		this.bonusScore = bonusScore;
	}

	public int getPoint() {
		return score;
	}

	public String getName() {
		return name;
	}

	public boolean isPromotable(int score) {
		return promotionJudge.test(score);
	}

	public int getBonusPoint() {
		return bonusScore.get();
	}
}
