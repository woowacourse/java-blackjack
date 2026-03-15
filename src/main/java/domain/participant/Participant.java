package domain.participant;

import domain.card.Card;
import domain.card.CardBoard;
import java.util.List;

public abstract class Participant {
    protected final CardBoard cardBoard = new CardBoard();

    public void addCard(Card card) {
        cardBoard.add(card);
    }

    public int calculateScore() {
        return cardBoard.calculateScore();
    }

    public boolean checkScoreUnderCriterion() {
        return cardBoard.checkScoreUnderCriterion();
    }

    public boolean isBust() {
        return cardBoard.isBust();
    }

    public boolean isBlackjack() {
        return cardBoard.isBlackjack();
    }

    public List<Card> getCards() {
        return cardBoard.getCards();
    }
}
