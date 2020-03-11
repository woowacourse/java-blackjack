package domain.player;

import domain.BlackJackScoreManager;
import domain.card.HandCards;

public class Dealer extends BlackJackPlayer {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME, new HandCards());
    }

    @Override
    public boolean canDrawMore() {
        return calculateScore() <= BlackJackScoreManager.DEALER_DRAW_THRESHOLD;
    }
}
