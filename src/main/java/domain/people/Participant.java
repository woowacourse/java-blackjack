package domain.people;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.card.Hand;

public class Participant {
    private static final int BUST_BOUNDARY_VALUE = 21;
    private static final int BUST_HAND_VALUE = 0;

    private final Hand hand;
    private final String name;

    public Participant(List<Card> cards, String name) {
        this.hand = new Hand(cards);
        this.name = name;
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int getParticipantHandValue() {
        int participantHandValue = getHandValue();
        if (participantHandValue > BUST_BOUNDARY_VALUE) {
            participantHandValue = BUST_HAND_VALUE;
        }
        return participantHandValue;
    }

    public int getHandValue() {
        return hand.calculateValue();
    }

    public List<String> getCardNames() {
        return hand.getCardNames();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Participant that = (Participant)o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
