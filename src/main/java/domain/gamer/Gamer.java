package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardGroup;

import java.util.List;

import static domain.GameManager.LIMIT;

public class Gamer {
    private final CardGroup cardGroup;
    private final CardGenerator cardGenerator;

    public Gamer(CardGroup cardGroup, CardGenerator cardGenerator) {
        this.cardGroup = cardGroup;
        this.cardGenerator = cardGenerator;
    }

    public void receiveCard() {
        cardGroup.addCard(cardGenerator.peekRandomCard());
    }

    public void receiveCard(int count) {
        for (int i = 0; i < count; i++) {
            receiveCard();
            ;
        }
    }

    public boolean isBust() {
        return this.cardGroup.calculateScore() > LIMIT;
    }

    public GameResult calculateGameResult(final int compareScore) {
        if (isBust()) {
            return GameResult.LOSE;
        }
        return GameResult.findByScores(cardGroup.calculateScore(), compareScore);
    }

    public int calculateScore() {
        return cardGroup.calculateScore();
    }

    public List<Card> getCards() {
        return cardGroup.getCards();
    }
}
