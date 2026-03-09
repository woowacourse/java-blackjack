package domain;

import constant.PolicyConstant;

public class Dealer extends Participant {

    private final String name = PolicyConstant.DEALER_NAME;

    public Dealer(String name) {
        super();
    }

    public Hand getOnlyFirstHand() {
        Hand newHand = new Hand();
        newHand.addCard(hand.getFirstCard());
        return newHand;
    }
}
