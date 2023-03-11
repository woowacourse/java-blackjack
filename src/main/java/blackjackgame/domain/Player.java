package blackjackgame.domain;

import java.util.List;

public abstract class Player {
    private final Hand hand;

    public Player(final Hand hand) {
        this.hand = hand;
    }

    public abstract boolean canHit();

    public abstract String getName();

    public int getScore() {
        return hand.getScore();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
}
