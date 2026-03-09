package domain;

import domain.player.Hand;

public abstract class Participant {
    private final Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public CardDto toDto() {
        return hand.handToDto();
    }
}
