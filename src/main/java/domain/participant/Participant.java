package domain.participant;

import static domain.constant.GameRule.BLACKJACK_CRITERION;
import static domain.constant.GameRule.INIT_CARD_COUNT;

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
        return cardBoard.calculateScore() <= BLACKJACK_CRITERION;
    }

    public List<Card> getCards() {
        return cardBoard.getCards();
    }

    public boolean isBust() {
        return cardBoard.isBust();
    }

    public boolean isBlackjack() {
        if (cardBoard.getCards().size() != INIT_CARD_COUNT) {
            return false;
        }
        return cardBoard.calculateScore() == BLACKJACK_CRITERION;
    }
}
