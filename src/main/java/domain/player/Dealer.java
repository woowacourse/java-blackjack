package domain.player;

import domain.card.Card;
import java.util.List;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int BOUNDARY = 16;

    public Dealer(Hand hand) {
        super(NAME, hand);
    }

    public boolean needsToHit() {
        return super.getHand().getTotalScore() <= BOUNDARY;
    }

    public List<Card> getCards() {
        return super.getCards();
    }

    public int getBoundary() {
        return BOUNDARY;
    }
}
