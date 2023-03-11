package domain.user;

import domain.card.Card;
import domain.card.Hand;

import java.util.ArrayList;
import java.util.List;


public class Dealer extends User {
    private static final int DEALER_HIT_LIMIT = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        this(new ArrayList<>());
    }

    public Dealer(List<Card> cards) {
        super(new Hand(cards));
    }

    @Override
    public boolean canHit() {
        return score().isLessThan(DEALER_HIT_LIMIT);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}


