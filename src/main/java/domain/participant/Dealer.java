package domain.participant;

import domain.card.CardDeck;
import java.util.ArrayList;

public class Dealer implements Participant{
    private static final int DEALER_DRAW_THRESHOLD = 16;

    private final CardDeck hand;

    public Dealer() {
        this.hand = new CardDeck(new ArrayList<>());
    }

    @Override
    public void hitCards(final CardDeck standard) {
        hand.hitCards(standard);
    }

    public void draw(final CardDeck standard) {
        if (isUnderThreshold()) {
            hand.addCard(standard.hitCard());
        }
    }

    @Override
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

