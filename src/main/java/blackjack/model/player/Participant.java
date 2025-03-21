package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.game.ReceivedCards;

public abstract class Participant {
    private final ReceivedCards receivedCards = new ReceivedCards();

    protected Participant() {
    }

    public void putCard(Card card) {
        receivedCards.receive(card);
    }

    public void initialCard(Card card1, Card card2) {
        putCard(card1);
        putCard(card2);
    }

    public ReceivedCards getReceivedCards() {
        return receivedCards;
    }

    public boolean isBust() {
        return receivedCards.isBust();
    }

    public int calculatePoint() {
        return receivedCards.calculateTotalPoint();
    }
}
