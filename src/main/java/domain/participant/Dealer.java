package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;

public class Dealer extends Participant {
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Card getHandExceptHidden() {
        return hand.getCardExceptHidden();
    }

    public void draw(final CardDeck standard) {
        while (isUnderThreshold()) {
            hand.addCard(standard.hitCard());
        }
    }

    public boolean isUnderThreshold() {
        return sum() <= DEALER_DRAW_THRESHOLD;
    }

}

