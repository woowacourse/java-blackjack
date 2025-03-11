package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGroup;

import java.util.List;

import static domain.GameManager.LIMIT;

public class Gamer {
    private final CardGroup cardGroup;

    public Gamer(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
    }

    public void receiveCard(Card card) {
        cardGroup.addCard(card);
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
