package domain.participant;

import domain.card.Card;
import domain.vo.Name;

public abstract class Participant {
    private static final int BUST_CONDITION = 22;
    protected Name name;
    protected HandCards handCards = new HandCards();

    public void drawCard(Card card) {
        handCards.drawCard(card);
    }

    public boolean isBust() {
        return handCards.calculateCardsScore() >= BUST_CONDITION;
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return handCards.calculateCardsScore();
    }

    public abstract void finalizeResult(int score);
}
