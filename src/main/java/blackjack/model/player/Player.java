package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.game.ReceivedCards;

public abstract class Player {
    private final ReceivedCards receivedCards = new ReceivedCards();

    protected Player() {
    }

    public void putCard(final Card card) {
        receivedCards.receive(card);
    }

    public boolean isBust() {
        return receivedCards.isBust();
    }

    public boolean isBlackJack() {
        return receivedCards.isBlackJack();
    }

    public int calculatePoint() {
        return receivedCards.calculateTotalPoint();
    }

    public ReceivedCards getReceivedCards() {
        return receivedCards;
    }
}
