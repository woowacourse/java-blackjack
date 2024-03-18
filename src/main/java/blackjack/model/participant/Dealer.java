package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public class Dealer extends Gamer {
    private static final int HITTABLE_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(final Hand hand) {
        super(new Name(DEALER_NAME), hand);
    }

    @Override
    public boolean canHit() {
        return hand.calculateScore() <= HITTABLE_THRESHOLD;
    }

    public List<Card> openCard() {
        return List.of(hand.getFirstCard());
    }
}
