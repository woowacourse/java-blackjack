package team.blackjack.domain;

import java.util.List;

public class Participant {
    private final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public int getScore() {
        return this.hand.getScore();
    }

    public void hit(Card card) {
        if (isBust()) {
            throw new IllegalStateException("이미 Bust 상태입니다.");
        }

        this.hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }
}
