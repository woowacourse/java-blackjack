package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public class Dealer extends Player {
    private static final int HITTABLE_THRESHOLD = 16;

    public Dealer(final Hand hand) {
        super("딜러", hand);
    }

    @Override
    public boolean canHit() {
        return hand.calculateScore() <= HITTABLE_THRESHOLD;
    }

    public List<Card> openCard() {
        return List.of(hand.getFirstCard());
    }
}
