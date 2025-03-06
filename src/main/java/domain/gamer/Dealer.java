package domain.gamer;

import domain.card.CardGenerator;
import domain.card.CardGroup;

public class Dealer extends Gamer {
    public Dealer(CardGroup cardGroup, CardGenerator cardGenerator) {
        super(cardGroup, cardGenerator);
    }

    public boolean isLessThen(int score) {
        return this.cardGroup.calculateScore(Gamer.LIMIT) <= score;
    }
}
