package blackjack.domain;

import java.util.List;

public class Dealer extends Participant {

    private static final int MAXIMUM_SCORE_FOR_ADDITIONAL_CARD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        int score = calculateScore();
        return score <= MAXIMUM_SCORE_FOR_ADDITIONAL_CARD;
    }

    public BetAmount calculateFinalBetProfit(List<Player> players) {
        BetAmount finalBetProfit = BetAmount.ZERO;
        for (Player player : players) {
            finalBetProfit = finalBetProfit.add(player.calculateFinalBetProfit(this));
        }
        return finalBetProfit.toNegative();
    }
}
