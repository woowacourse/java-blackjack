package blackjack.domain.rule;

import blackjack.domain.player.Hand;

public class ScoreCalculateStrategy {

    private static final int BLACK_JACK = 21;
    private static final int ACE_WEIGHT = 10;

    public Score calculate(Hand hand) {
        int aceCount = hand.countAce();
        int sum = hand.calculateCardSummation();
        while (aceCount > 0 && (sum + ACE_WEIGHT) <= BLACK_JACK) {
            sum += ACE_WEIGHT;
            aceCount--;
        }
        return new Score(sum);
    }
}
