package domain;

import domain.card.Card;
import domain.participant.Participant;

public class Dealer extends Participant {

    public Card getFirstCard() {
        return hand.getFirst();
    }
}
