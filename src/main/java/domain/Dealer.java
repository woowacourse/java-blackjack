package domain;

import java.util.List;

public class Dealer {
    private static final String DEALER_NAME = "딜러";
    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public void keepCard(Card card) {
        hand.addCard(card);
    }

    public Integer handSize() {
        return hand.getHandSize();
    }

    public Integer getTotalCardScore() {
        return hand.calculateTotalScore();
    }

    public Boolean dealerRule() {
        return getTotalCardScore() < 17;
    }

    public String getName() {
        return DEALER_NAME;
    }

    public Hand getHand() {
        return hand;
    }
}
