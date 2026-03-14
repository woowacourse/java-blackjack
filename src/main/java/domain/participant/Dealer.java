package domain.participant;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_MAX_SCORE = 16;

    public Dealer() {
        super();
    }

    public List<Card> getOnlyFirstHand() {
        List<Card> newHand = new ArrayList<>();
        newHand.add(hand.getFirstCard());
        return newHand;
    }

    public boolean shouldHit() {
        return this.calculateScore() <= DEALER_HIT_MAX_SCORE;
    }

    public String getName() {
        return DEALER_NAME;
    }
}
