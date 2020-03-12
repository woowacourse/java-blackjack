package domain.participant;

import domain.ParticipantCards;

public class Dealer extends Participant {
    public Dealer() {
        this.name = "딜러";
        this.cards = new ParticipantCards();
    }

    public String toStringFirstDraw() {
        return name + " : " + cards.toStringOneCard();
    }
}
