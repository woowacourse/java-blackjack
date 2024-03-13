package blackjack.player;

import blackjack.card.Card;
import java.util.List;

public class Dealer extends Player {

    private static final int MAX_DRAWABLE_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    @Override
    public boolean hasDrawableScore() {
        return hand.calculateScore() <= MAX_DRAWABLE_SCORE;
    }

    @Override
    public List<Card> revealCardsOnFirstPhase() {
        return List.of(hand.getFirstCard());
    }
}
