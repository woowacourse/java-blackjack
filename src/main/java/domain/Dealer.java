package domain;

import constant.GameConstant;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {

    public Dealer(String name) {
        super(name);
    }

    public boolean needAdditionalCard() {
        return this.calculateScore() <= GameConstant.DEALER_HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }
}
