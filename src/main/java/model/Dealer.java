package model;

import model.cardGettable.EveryCardsGettable;
import model.cardGettable.FirstCardsGettable;

public class Dealer extends Participator {
    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_CRITERIA_HIT_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
        setCardsGettableStrategy(new FirstCardsGettable());
    }

    public void setEveryCardGettable() {
        setCardsGettableStrategy(new EveryCardsGettable());
    }

    @Override
    public boolean canReceiveCard() {
        return this.getSum() <= DEALER_CRITERIA_HIT_SCORE;
    }

    public void receiveBet(long bettingAmount) {

    }
}
