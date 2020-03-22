package domain.rule;

import domain.card.Hand;
import domain.result.Score;

public class Rule {
	private static final int PLAYER_HIT_CEILING = 21;
	private static final int DEALER_HIT_CEILING = 17;

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

	public static boolean canPlayerHit(Hand hand) {
		return Score.from(hand).isLowerThan(Score.from(PLAYER_HIT_CEILING));
	}

	public static boolean canDealerHit(Hand hand) {
		return Score.from(hand).isLowerThan(Score.from(DEALER_HIT_CEILING));
	}
}
