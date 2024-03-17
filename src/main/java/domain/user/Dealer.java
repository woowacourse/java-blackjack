package domain.user;

import domain.card.Card;

public class Dealer {
    private static final int RECEIVABLE_THRESHOLD = 16;
    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public boolean isReceivable() {
        return sumCard() <= RECEIVABLE_THRESHOLD;
    }

    public void receive(Card card) {
        hand.receive(card);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public int sumCard() {
        return hand.sumCard();
    }

    public Hand getHand() {
        return hand;
    }
}
