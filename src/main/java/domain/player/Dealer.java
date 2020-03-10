package domain.player;

import domain.ParticipantCards;

public class Dealer extends Participant {
    public Dealer() {
        this.name = "딜러";
        this.cards = new ParticipantCards();
    }

    public String toStringOneCard() {
        return name + " : " + cards.toStringOneCard();
    }
}
