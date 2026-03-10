package domain.participant;

import static domain.constant.GameRule.BLACKJACK_CRITERION;

import domain.card.Card;
import domain.card.CardBoard;
import java.util.List;

public abstract class Participant {

    protected final CardBoard cardBoard = new CardBoard();

    public void addCard(Card card) {
        cardBoard.add(card);
    }

    public int getScore() {
        return cardBoard.calculateScore();
    }

    public boolean checkScoreUnderCriterion() {
        return cardBoard.calculateScore() <= BLACKJACK_CRITERION;
    }

    public List<Card> getCards() {
        return cardBoard.getCards();
    }
}
