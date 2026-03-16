package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.Score;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;
    private static final Score HIT_THRESHOLD_SCORE = new Score(HIT_THRESHOLD);

    public Dealer(final Hand hand) {
        super(new Name(DEALER_NAME), hand);
    }

    public Card getOpenCard() {
        return getCards().getFirst();
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore().isLessThanOrEqualTo(HIT_THRESHOLD_SCORE);
    }
}
