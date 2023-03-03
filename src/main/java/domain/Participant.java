package domain;

import java.util.List;

abstract class Participant {

    private final int upperBoundOfDrawable;

    private final Hand hand;

    Participant(int upperBoundOfDrawable) {
        this.upperBoundOfDrawable = upperBoundOfDrawable;
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public boolean isDrawable() {
        return upperBoundOfDrawable > hand.calculateScore();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
}
