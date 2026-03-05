package domain;

import domain.card.Card;

public abstract class Participant {
    private String name;
    private Hand hand;

    public Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void receiveInitialCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            receive(deck);
        }
    }

    public void receive(Deck deck) {
        Card card = deck.draw();
        hand.add(card);
    }

    public int handSize() {
        return hand.size();
    }

    public int score() {
        return hand.score();
    }

    public abstract void shouldReceive(Deck deck);
}
