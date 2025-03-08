package domain.participant;

import domain.card.CardDeck;
import java.util.ArrayList;

public class Dealer {
    private static final int DEALER_DRAW_THRESHOLD = 16;
    private final int INITIAL_HIT_COUNT = 2;

    private final CardDeck hand;

    public Dealer(){
        this.hand = new CardDeck(new ArrayList<>());
    }

    public void hitCards(final CardDeck standard) {
        for (int i = 0; i < INITIAL_HIT_COUNT; i++) {
            hand.addCard(standard.hitCard());
        }
    }

    public void draw(final CardDeck standard) {
        if (isUnderThreshold()) {
            hand.addCard(standard.hitCard());
        }
    }

    public int sum() {
        return hand.sum();
    }

    public boolean isUnderThreshold() {
        return sum() <= DEALER_DRAW_THRESHOLD;
    }

    public CardDeck getHand() {
        return hand;
    }
}

