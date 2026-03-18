package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {

    public Card getFirstCard() {
        return hand.getFirstCard();
    }
}
