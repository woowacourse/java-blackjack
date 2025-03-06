package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardGroup;

public abstract class Gamer {
    public static final int LIMIT = 21;

    protected final CardGroup cardGroup;
    protected final CardGenerator cardGenerator;

    protected Gamer(CardGroup cardGroup, CardGenerator cardGenerator) {
        this.cardGroup = cardGroup;
        this.cardGenerator = cardGenerator;
    }

    public boolean receiveCard(final Card card) {
        return cardGroup.addCard(card);
    }

    public void receiveCard(){
        cardGroup.addCard(cardGenerator.generate());
    }

    public boolean isBust() {
        return this.cardGroup.calculateScore(LIMIT) > LIMIT;
    }

    public GameResult calculateGameResult(final int compareScore) {
        return GameResult.findByScores(cardGroup.calculateScore(LIMIT), compareScore);
    }
}
