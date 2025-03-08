package domain.gamer;

import domain.card.CardGenerator;
import domain.card.CardGroup;

public class Dealer extends Player {
    private static final String DEALER_NAME = "NEO";
    private final int DEALER_HIT_ROLE = 16;

    public Dealer(CardGroup cardGroup, CardGenerator cardGenerator) {
        super(DEALER_NAME,cardGroup, cardGenerator);
    }

    public boolean isLessThen(int score) {
        return this.cardGroup.calculateScore(Player.LIMIT) <= score;
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
