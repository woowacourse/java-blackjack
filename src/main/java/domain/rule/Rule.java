package domain.rule;

import domain.card.Hand;
import domain.result.Score;

public class Rule {
	public static boolean isBust(Hand hand) {
		return Score.from(hand).isBiggerThan(Score.BLACKJACK);
	}

	public static boolean isNotBust(Hand hand) {
		return !isBust(hand);
	}

	public static boolean isBlackJack(Hand hand) {
		return hand.hasTwoCards() &&
				Score.from(hand).isEqualTo(Score.BLACKJACK);
	}

	public static boolean isNotBlackJack(Hand hand) {
		return !isBlackJack(hand);
	}

	public static boolean canHit(Hand hand, int hitThreshold) {
		return Score.from(hand).isLowerThan(Score.from(hitThreshold));
	}
}
