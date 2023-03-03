package domain;

import java.util.List;

abstract class Participant {

    private final int upperBoundOfDrawable;

    protected final Hand hand;

    Participant(int upperBoundOfDrawable) {
        this.upperBoundOfDrawable = upperBoundOfDrawable;
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
}
