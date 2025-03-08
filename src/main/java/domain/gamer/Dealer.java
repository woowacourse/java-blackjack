package domain.gamer;

import domain.card.CardGenerator;
import domain.card.CardGroup;

public class Dealer extends Gamer {
    private final int DEALER_HIT_ROLE = 16;

    public Dealer(CardGroup cardGroup, CardGenerator cardGenerator) {
        super(cardGroup, cardGenerator);
    }

    public boolean isLessThen(int score) {
        return this.cardGroup.calculateScore(Gamer.LIMIT) <= score;
    }

    public int giveCardsToDealer() {
        int count = 0;
        while (isLessThen(DEALER_HIT_ROLE)) {
            receiveCard();
            count++;
        }
        return count;
    }
}
