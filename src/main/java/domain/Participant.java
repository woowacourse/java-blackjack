package domain;

import java.util.List;

public abstract class Participant {
    private static final Integer BLACKJACK_SCORE = 21;

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateTotalScore();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean checkBust() {
        if (hand.calculateTotalScore() > BLACKJACK_SCORE) {
            return true;
        }
        return false;
    }
}
