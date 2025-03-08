package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardGroup;
import java.util.List;

public abstract class Gamer {
    public static final int LIMIT = 21;

    protected final CardGroup cardGroup;
    protected final CardGenerator cardGenerator;

    protected Gamer(CardGroup cardGroup, CardGenerator cardGenerator) {
        this.cardGroup = cardGroup;
        this.cardGenerator = cardGenerator;
    }

    public void receiveCard(){
        cardGroup.addCard(cardGenerator.generate());
    }

    public void receiveCard(int count) {
        while (count-- > 0) {
            cardGroup.addCard(cardGenerator.generate());
        }
    }

    public boolean isBust() {
        return this.cardGroup.calculateScore(LIMIT) > LIMIT;
    }

    public GameResult calculateGameResult(final int compareScore) {
        if (isBust()) {
            return GameResult.LOSE;
        }
        return GameResult.findByScores(cardGroup.calculateScore(LIMIT), compareScore);
    }

    public int calculateScore() {
        return cardGroup.calculateScore(LIMIT);
    }

    public List<Card> getCards() {
        return cardGroup.getCards();
    }
}
