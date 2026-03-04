package team.blackjack.domain.rule;

import team.blackjack.domain.Dealer;
import team.blackjack.domain.Hand;

public class DefaultBlackjackRule {
    public static final int BLACKJACK = 21;
    public static final int BLACKJACK_CARD_COUNT = 2;
    public static final int DEALER_STAND_SCORE = 17;

    public boolean isBust(Hand hand) {
        return hand.getScore() > BLACKJACK;
    }

    public boolean isBlackjack(Hand hand) {
        if (hand.getCardCount() != BLACKJACK_CARD_COUNT) {
            return false;
        }
        return hand.getScore() == BLACKJACK;
    }

    public boolean isDealerMustDraw(Dealer dealer) {
        return dealer.getScore() < DEALER_STAND_SCORE;
    }
}
