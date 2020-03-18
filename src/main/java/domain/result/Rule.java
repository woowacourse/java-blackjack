package domain.result;

import java.util.function.Function;

import domain.gamer.Gamer;

public enum Rule {
	BLACK_JACK("블랙잭", gamer -> gamer.hasTwoCards() && calculateScore(gamer).isEqualTo(Score.BLACKJACK)),
	BUST("파산", gamer -> calculateScore(gamer).isBiggerThan(Score.BLACKJACK)),
	CAN_HIT("추가지급가능", gamer -> calculateScore(gamer).isLowerThan(Score.from(gamer.getHitPoint())));

	private final String rule;
	private final Function<Gamer, Boolean> expression;

	Rule(String rule, Function<Gamer, Boolean> expression) {
		this.rule = rule;
		this.expression = expression;
	}

	public boolean apply(Gamer gamer) {
		return this.expression.apply(gamer);
	}

	public static boolean canHit(Gamer gamer) {
		return CAN_HIT.apply(gamer);
	}

	public static boolean isBlackJack(Gamer gamer) {
		return BLACK_JACK.apply(gamer);
	}

	public static boolean isNotBlackJack(Gamer gamer) {
		return !isBlackJack(gamer);
	}

	public static boolean isBust(Gamer gamer) {
		return BUST.apply(gamer);
	}

	public static boolean isNotBust(Gamer gamer) {
		return !isBust(gamer);
	}

	public static Score calculateScore(Gamer gamer) {
		return Score.from(gamer);
	}
}
