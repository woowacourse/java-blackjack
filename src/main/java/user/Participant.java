package user;

import card.Deck;

public class Participant {
    private ParticipantName name;
    private Hands hands;

    public Participant(ParticipantName name, Deck deck) {
        this.name = name;
        this.hands = new Hands(deck);
    }

    public int handSize() {
        return hands.size();
    }
}
