package domain;

import java.util.List;

public abstract class Participant {

    protected final String name;
    protected final Hand hand;

    protected Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void addDefaultCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public void addCard(Card receivedCard) {
        hand.add(receivedCard);
    }

    abstract boolean isPossibleDraw();
}
