package domain.result;

import domain.gamer.Gamer;

public class Rule {
	public static Score calculateScore(Gamer gamer) {
		return Score.from(gamer);
	}

	public static boolean isBlackJack(Gamer gamer) {
		return gamer.hasTwoCards() && calculateScore(gamer).isEqualTo(Score.BLACKJACK);
	}

	public static boolean isNotBlackJack(Gamer gamer) {
		return !isBlackJack(gamer);
	}

	public static boolean isBust(Gamer gamer) {
		return calculateScore(gamer).isBiggerThan(Score.BLACKJACK);
	}

	public static boolean isNotBust(Gamer gamer) {
		return !isBust(gamer);
	}

	public static boolean canHit(Gamer gamer) {
		return calculateScore(gamer).isLowerThan(Score.from(gamer.getHitPoint()));
	}
}
