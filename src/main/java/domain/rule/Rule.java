package domain.rule;

import domain.card.Hand;
import domain.result.Score;

public class Rule {
	private static final Score PLAYER_HIT_CEILING = Score.from(21);
	private static final Score DEALER_HIT_CEILING = Score.from(17);
	private static final int TEN = 10;
	private static final int BLACKJACK_SCORE = 21;

	public static boolean isBust(Hand hand) {
		return newScore(hand).isBiggerThan(Score.BLACKJACK);
	}

	public static boolean isNotBust(Hand hand) {
		return !isBust(hand);
	}

	public static boolean isBlackJack(Hand hand) {
		return hand.hasTwoCards() &&
				newScore(hand).isEqualTo(Score.BLACKJACK);
	}

	public static boolean isNotBlackJack(Hand hand) {
		return !isBlackJack(hand);
	}

	public static boolean canPlayerHit(Hand hand) {
		return newScore(hand).isLowerThan(PLAYER_HIT_CEILING);
	}

	public static boolean canDealerHit(Hand hand) {
		return newScore(hand).isLowerThan(DEALER_HIT_CEILING);
	}

	public static Score newScore(Hand hand) {
		int score = hand.sumOfCards();

		return Score.from(reviseAceScore(hand.hasAce(), score));
	}

	private static int reviseAceScore(boolean hasAce, int score) {
		if (hasAce && (score + TEN <= BLACKJACK_SCORE)) {
			score += TEN;
		}
		return score;
	}
}
