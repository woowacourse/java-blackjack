package player;

import card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int MAX_DRAWABLE_SCORE = 16;

    public Dealer() {
        super("딜러");
    }

    Dealer(Hand hand) {
        super("딜러", hand);
    }

    @Override
    public boolean hasDrawableScore() {
        return hand.calculateScore() <= MAX_DRAWABLE_SCORE;
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }
}
