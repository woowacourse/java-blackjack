package blackjack;

import domain.card.Cards;

public class BlackjackUtils {
	private static final int BLACKJACK_SCORE = 21;
	private static final int BLACKJACK_CARD_SIZE = 2;
	private static final int BUST_MIN_SCORE = 22;

	public static boolean isBlackjack(Cards cards) {
		return cards.size() == BLACKJACK_CARD_SIZE && cards.calculateScore() == BLACKJACK_SCORE;
	}

	public static boolean isBust(Cards cards) {
		return cards.calculateScore() >= BUST_MIN_SCORE;
	}
}
