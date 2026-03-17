package team.blackjack.domain;

import java.util.Set;

public abstract class Participant {
    private final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public int getScore() {
        return this.hand.getScore();
    }

    public void hit(Card card) {
        if (isBust()) {
            throw new IllegalStateException("이미 Bust 상태입니다. 더 이상 카드를 받을 수 없습니다.");
        }
        this.hand.addCard(card);
    }

    public Set<Card> getCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }
}
