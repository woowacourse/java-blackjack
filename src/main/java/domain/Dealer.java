package domain;

import constant.GameConstant;

public class Dealer extends Player {
    public static final String DEALER_NAME = "딜러";

    public Dealer(String name) {
        super(name);
    }

    public boolean needAdditionalCard() {
        return hand.calculateFinalScore() <= GameConstant.DEALER_HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        return hand.getFirst();
    }
}
