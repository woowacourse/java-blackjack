package domain.participant;

import domain.ParticipantCards;
import domain.card.CardDeck;

public class Dealer extends Participant {
    private static final int DEALER_STANDARD_SCORE = 16;

    public Dealer() {
        this.name = "딜러";
        this.cards = new ParticipantCards();
    }

    public String toStringFirstDraw() {
        return name + " : " + cards.toStringOneCard();
    }

    public int performHit(CardDeck cardDeck) {
        int hitNumber = 0;
        while (this.calculateScore() <= DEALER_STANDARD_SCORE) {
            hitNumber++;
            this.receive(cardDeck);
        }
        return hitNumber;
    }
}
