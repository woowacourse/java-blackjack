package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import java.util.ArrayList;

public class Dealer {
    private static final int DEALER_DRAW_THRESHOLD = 16;

    private final CardDeck hand;

    public Dealer() {
        this.hand = new CardDeck(new ArrayList<>());
    }

    public void hitCards(final CardDeck standard) {
        hand.hitCards(standard);
    }

    public Card getHandExceptHidden(){
        return hand.getCardExceptHidden();
    }

    public void draw(final CardDeck standard) {
        if (isUnderThreshold()) {
            hand.addCard(standard.hitCard());
        }
    }

    public int sum() {
        return hand.sumWithAce();
    }

    public boolean isUnderThreshold() {
        return sum() <= DEALER_DRAW_THRESHOLD;
    }

    public CardDeck getHand() {
        return hand;
    }
}

