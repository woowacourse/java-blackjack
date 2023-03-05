package domain.people;

import java.util.ArrayList;
import java.util.List;

import domain.card.Deck;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    public Dealer() {
        super(new ArrayList<>(), DEALER_NAME);
    }

    public void deal(Deck deck, List<Participant> participants) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.draw());
        }
    }
}
