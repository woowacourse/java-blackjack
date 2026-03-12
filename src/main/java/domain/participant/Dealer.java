package domain.participant;

import domain.Hand;

public class Dealer extends Participant {
    private static final int MAX_DEALER_SHOULD_RECEIVE_SCORE = 16;

    public Dealer() {
        super("딜러", new Hand());
    }

    public String getOpenCard() {
        return hand.showHand().getFirst();
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() <= MAX_DEALER_SHOULD_RECEIVE_SCORE;
    }
}
