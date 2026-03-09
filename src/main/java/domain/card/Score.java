package domain.card;

import static util.BlackJackConstant.ACE_ADJUSTMENT_VALUE;
import static util.BlackJackConstant.BLACKJACK_SCORE;

public class Score {

    public static int calculate(Hand hand) {
        int sum = 0;
        int aceCount = 0;

        for (Card card : hand.getHand()) {
            sum += card.getScore();
            aceCount = countAce(card, aceCount);
        }

        sum = handleAce(sum, aceCount);

        return sum;
    }

    private static int countAce(Card card, int aceCount) {
        if (card.isAce()) {
            aceCount++;
        }
        return aceCount;
    }

    private static int handleAce(int sum, int aceCount) {
        for (int i = 0; i < aceCount; i++) {
            if (sum <= BLACKJACK_SCORE) {
                break;
            }
            sum -= ACE_ADJUSTMENT_VALUE;
        }
        return sum;
    }
}
