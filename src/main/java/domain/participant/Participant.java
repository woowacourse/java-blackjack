package domain.participant;

import domain.card.Card;

import java.util.List;

public abstract class Participant {
    protected static final int BLACKJACK_CONDITION = 21;
    protected HandCards handCards;

    public Participant(HandCards handCards) {
        this.handCards = handCards;
    }

    public void drawCard(Card card) {
        handCards.drawCard(card);
    }

    public boolean isBust() {
        return handCards.calculateCardsScore() > BLACKJACK_CONDITION;
    }

    public List<String> getCards() {
        return handCards.getCardNames();
    }

    public int getScore() {
        return handCards.calculateCardsScore();
    }

    public boolean isBlackjack() {
        return handCards.getHandCardsSize() == 2 && getScore() == BLACKJACK_CONDITION;
    }
}
