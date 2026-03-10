package domain;

import constant.GameConstant;

public class Dealer extends Player {
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean needAdditionalCard() {
        return this.calculateScore() <= GameConstant.ADDITIONAL_THRESHOLD;
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }
}
