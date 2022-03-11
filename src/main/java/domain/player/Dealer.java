package domain.player;

import domain.ScoreUtil;

public class Dealer extends Player {
    private static final int MORE_CARD_CRITERIA = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_DEALER_NAME);
    }

    public Dealer(String name) {
        super(name);
    }

    public boolean isUnderSixteen() {
        return ScoreUtil.getScore(getPlayingCards()) <= MORE_CARD_CRITERIA;
    }
}
