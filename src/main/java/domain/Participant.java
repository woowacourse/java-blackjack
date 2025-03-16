package domain;

import java.util.List;

public abstract class Participant {

    protected final Hand hand;

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void addDefaultCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public void addCard(Card receivedCard) {
        if (isPossibleDraw()) {
            hand.add(receivedCard);
        }
    }

    abstract boolean isPossibleDraw();
}
