package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer {
    private static final int NO_MORE_DRAW_NUMBER = 16;
    private final Hand hand;

    public Dealer() {
        this.hand = Hand.createEmptyHand();
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isUnderSixteen() {
        return hand.calculateScore() < NO_MORE_DRAW_NUMBER;
    }
}
