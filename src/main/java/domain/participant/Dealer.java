package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    public Dealer() {
        super("딜러");
    }

    public Card getFirstCard() {
        return getHandCards().getFirst();
    }

    public boolean isReceiveCard() {
        return calculateScore() <= 16;
    }
}
