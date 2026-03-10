package domain;

import constant.PolicyConstant;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    public Dealer() {
        super();
    }

    public List<String> getOnlyFirstHand() {
        List<String> newHand = new ArrayList<>();
        newHand.add(hand.getFirstCard());
        return newHand;
    }

    public String getName() {
        return PolicyConstant.DEALER_NAME;
    }
}
