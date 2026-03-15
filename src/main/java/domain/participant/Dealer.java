package domain.participant;

import constant.GameConstant;
import domain.card.Card;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";

    public Dealer(String name) {
        super(name);
    }

    public boolean needAdditionalCard() {
        return getFinalScore() <= GameConstant.DEALER_HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        return hand.getFirst();
    }
}
