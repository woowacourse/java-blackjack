package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import java.util.ArrayList;

public class Dealer {
    private static final int DEALER_DRAW_THRESHOLD = 16;

    private final CardDeck standard;
    private final CardDeck hand;

    public Dealer(final CardDeck standard) {
        this.standard = standard;
        this.hand = new CardDeck(new ArrayList<>());
    }

    public Card hitCard() {
        return standard.hitCard();
    }

    public void addCards() {
        hand.addCard(hitCard());
    }

    public void draw() {
        if (isUnderThreshold()) {
            addCards();
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
