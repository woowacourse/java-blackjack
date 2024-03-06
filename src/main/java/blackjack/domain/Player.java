package blackjack.domain;

import java.util.List;

public class Player {
    private final Hand hand;

    public Player(final Hand hand) {
        this.hand = hand;
    }

    public void addCards(final Card... cards) {
        for (Card card : cards) {
            hand.add(card);
        }
    }

    public List<Card> getCards() {
        return hand.getAllCards();
    }

    public boolean isDead() {
        return hand.getSum() > 21;
    }

    // TODO : 꼭 필요한 메서드일까?
    public int getScore() {
        return hand.getSum();
    }
}
