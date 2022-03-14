package blackjack.domain.participant;

import blackjack.domain.card.Hand;

public class Dealer extends Participant {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int DEALER_OVER_SCORE = 17;

    public Dealer() {
        this(new Hand());
    }

    public Dealer(Hand hand) {
        this(DEALER_NAME, hand);
    }

    private Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    @Override
    public boolean shouldReceive() {
        return cardHand.getScore() < DEALER_OVER_SCORE && !cardHand.isBust()
            && !cardHand.isBlackjack();
    }
}
