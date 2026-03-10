package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    private static final int DEALER_MAX_HITTABLE_SCORE = 17;
    private static final String DEALER_NAME = "딜러";
    private static int DEALER_BETTING_MONEY=0;

    public Dealer() {
        super(new ParticipantInfo(DEALER_NAME, new Hand()), new Money(DEALER_BETTING_MONEY));
    }

    @Override
    public boolean canHit() {
        return getTotalCardScore() < DEALER_MAX_HITTABLE_SCORE;
    }

    @Override
    public void keepCard(Card card) {
        if (canHit()) {
            this.getHand().addCard(card);
        }
    }
}
