package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGroup;

import java.util.List;

import static domain.GameManager.BLACKJACK_NUMBER;
import static domain.GameManager.START_RECEIVE_CARD;

public class Gamer {
    private final CardGroup cardGroup;

    public Gamer(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
    }

    public boolean isBlackjack() {
        return cardGroup.countCards() == START_RECEIVE_CARD && calculateScore() == BLACKJACK_NUMBER;
    }

    public void receiveCard(Card card) {
        cardGroup.addCard(card);
    }

    public boolean isBust() {
        return this.cardGroup.calculateScore() > BLACKJACK_NUMBER;
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
