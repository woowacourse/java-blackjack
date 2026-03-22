package domain.participant;

import constant.PolicyConstant;
import domain.card.Hand;

public class Dealer extends Participant {

    public Dealer() {
        super(new Name(PolicyConstant.DEALER_NAME));
    }

    public Hand getOnlyFirstHand() {
        Hand newHand = new Hand();
        newHand.addCard(hand.getFirstCard());
        return newHand;
    }
}
