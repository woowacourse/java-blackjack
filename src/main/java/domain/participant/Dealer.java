package domain.participant;

import domain.card.CardDeck;
import domain.card.Hand;

public class Dealer extends Participant {
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public void draw(final CardDeck standard) {
        while (isUnderThreshold()) {
            hand.addCard(standard.hitCard());
        }
    }

    public boolean isUnderThreshold() {
        return sum() <= DEALER_DRAW_THRESHOLD;
    }

    @Override
    public Hand getFirstOpenHand() {
        return hand.getFirstCard();
    }

}

