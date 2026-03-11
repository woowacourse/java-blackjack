package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super();
    }

    public List<Card> getOnlyFirstHand() {
        List<Card> newHand = new ArrayList<>();
        newHand.add(hand.getFirstCard());
        return newHand;
    }

    public String getName() {
        return DEALER_NAME;
    }

    public boolean isDealer(String name) {
        return name.equals(DEALER_NAME);
    }
}
