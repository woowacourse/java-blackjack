package domain.participant;

import domain.card.CardDeck;

public class Dealer extends Participant {
    private static final int DEALER_STANDARD_SCORE = 16;

    public Dealer() {
        super("딜러");
    }

    public int performHit(CardDeck cardDeck) {
        int hitNumber = 0;
        while (this.calculateScore() <= DEALER_STANDARD_SCORE) {
            hitNumber++;
            this.receive(cardDeck.draw());
        }
        return hitNumber;
    }

    public String toStringFirstDraw() {
        return getName() + " : " + this.getCards().toStringOneCard();
    }
}
