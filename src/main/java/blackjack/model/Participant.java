package blackjack.model;

import java.util.List;

public abstract class Participant {

    public static final int BUST_SCORE = 21;
    private final HandCards handCards;

    public Participant() {
        this.handCards = new HandCards();
    }

    public final void receiveCard(Card card) {
        handCards.addCard(card);
    }

    public final Score getScore() {
        return handCards.calculate();
    }

    public final boolean isBust() {
        return getScore().getScore() > BUST_SCORE;
    }

    public boolean isBlackjack() {
        return handCards.isInitialCardSize() && getScore().isMaxScore();
    }

    public final Card firstCardOpen() {
        return handCards.getFirstCard();
    }

    public final List<Card> getHandCards() {
        return handCards.getCards();
    }

    public int totalScore() {
        return getScore().getScore();
    }

    public abstract boolean canReceive();
}
