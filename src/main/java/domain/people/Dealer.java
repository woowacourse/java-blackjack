package domain.people;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class Dealer {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_MINIMUM_VALUE = 17;

    private final Participant participant;
    public Dealer() {
        participant = new Participant(new ArrayList<>(), DEALER_NAME);
    }

    public void receiveCard(Card card) {
        participant.receiveCard(card);
    }
    public boolean shouldHit() {
        return participant.fetchHandValue() < DEALER_MINIMUM_VALUE;
    }

    public String getName() {
        return DEALER_NAME;
    }

    public List<Card> fetchHand() {
        return participant.fetchHand();
    }

    public int fetchHandValue() {
        return participant.fetchHandValue();
    }
}
