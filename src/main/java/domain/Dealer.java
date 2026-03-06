package domain;

import domain.card.Card;

import java.util.List;
import java.util.SequencedCollection;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canDraw() {
        int score = super.score();
        return score <= 16;
    }

    public Card getFirstCard() {
        return getAllCards().getFirst();
    }
}
